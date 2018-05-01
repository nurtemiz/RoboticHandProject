package com.example.nur.robotichandproject;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class ButtonControlFragment extends Fragment {

    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;
    byte[] readBuffer;
    int readBufferPosition;
    volatile boolean stopWorker;
    boolean connectionstate = false;
    byte BtAdapterSayac = 0;
    Button btOn, btConnect, btSettings;
    Button basParmakOn, basParmakOff, isaretParmakOn, isaretParmakOff, ortaParmakOn, ortaParmakOff, yuzukParmakOn;
    Button yuzukParmakOff, serceParmakOn, serceParmakOff;
    TextView MtxtVwState;
    String sGelenVeri;
    boolean bisChecked = false;

    public ButtonControlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewButtonControl = inflater.inflate(R.layout.fragment_button_control, container, false);

        btOn = (Button) viewButtonControl.findViewById(R.id.btOn);
        btConnect = (Button) viewButtonControl.findViewById(R.id.btConnect);
        btSettings = (Button) viewButtonControl.findViewById(R.id.btSettings);
        MtxtVwState = (TextView) viewButtonControl.findViewById(R.id.MtxtVwState);
        basParmakOn = (Button) viewButtonControl.findViewById(R.id.basParmakOn);
        basParmakOff = (Button) viewButtonControl.findViewById(R.id.basParmakOff);
        isaretParmakOn = (Button) viewButtonControl.findViewById(R.id.isaretParmakOn);
        isaretParmakOff = (Button) viewButtonControl.findViewById(R.id.isaretParmakOff);
        ortaParmakOn = (Button) viewButtonControl.findViewById(R.id.ortaParmakOn);
        ortaParmakOff = (Button) viewButtonControl.findViewById(R.id.ortaParmakOff);
        yuzukParmakOn = (Button) viewButtonControl.findViewById(R.id.yuzukParmakOn);
        yuzukParmakOff = (Button) viewButtonControl.findViewById(R.id.yuzukParmakOff);
        serceParmakOn = (Button) viewButtonControl.findViewById(R.id.serceParmakOn);
        serceParmakOff = (Button) viewButtonControl.findViewById(R.id.serceParmakOff);

        btOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bisChecked) {
                    try {
                        openBT();
                        findBT();
                        MtxtVwState.setText("Bluetooth Açıldı");
                        bisChecked = true;
                        btOn.setHint("Bluetooth Kapat");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        closeBT();
                        MtxtVwState.setText("Bluetooth Kapandı");
                        bisChecked = false;
                        btOn.setHint("Bluetooth Aç");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bisChecked != false) {
                    try {
                        openBT();
                        findBT();
                        MtxtVwState.setText("Bağlantı Kuruldu");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else
                    Toast.makeText(getActivity(), "Bağlantı kurmak için Bluetooth'u etkinleştiriniz. Lütfen!", Toast.LENGTH_LONG).show();
            }
        });

        btSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btsettings = new Intent(getActivity(), btsettings.class);
                getActivity().startActivity(btsettings);
            }
        });

        basParmakOn.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("EmptyCatchBlock")
            @Override
            public void onClick(View view) {
                try {
                    sendData("a");
                } catch (Exception ignored) {
                }
            }
        });

        basParmakOff.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("EmptyCatchBlock")
            @Override
            public void onClick(View view) {
                try {
                    sendData("e");
                } catch (Exception ignored) {
                }
            }
        });

        isaretParmakOn.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("EmptyCatchBlock")
            @Override
            public void onClick(View view) {
                try {
                    sendData("2");
                } catch (Exception ignored) {
                }
            }
        });

        isaretParmakOff.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("EmptyCatchBlock")
            @Override
            public void onClick(View view) {
                try {
                    sendData("7");
                } catch (Exception ignored) {
                }
            }
        });

        ortaParmakOn.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("EmptyCatchBlock")
            @Override
            public void onClick(View view) {
                try {
                    sendData("n");
                } catch (Exception ignored) {
                }
            }
        });

        ortaParmakOff.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("EmptyCatchBlock")
            @Override
            public void onClick(View view) {
                try {
                    sendData("t");
                } catch (Exception ignored) {
                }
            }
        });

        yuzukParmakOn.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("EmptyCatchBlock")
            @Override
            public void onClick(View view) {
                try {
                    sendData(" ");
                } catch (Exception ignored) {
                }
            }
        });

        yuzukParmakOff.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("EmptyCatchBlock")
            @Override
            public void onClick(View view) {
                try {
                    sendData(")");
                } catch (Exception ignored) {
                }
            }
        });

        serceParmakOn.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("EmptyCatchBlock")
            @Override
            public void onClick(View view) {
                try {
                    sendData("L");
                } catch (Exception ignored) {
                }
            }
        });

        serceParmakOff.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("EmptyCatchBlock")
            @Override
            public void onClick(View view) {
                try {
                    sendData("D");
                } catch (Exception ignored) {
                }
            }
        });

        return viewButtonControl;
    }

    /**********************************************************************************************
     * onCreate End
     *********************************************************************************************/
    void openBT() throws IOException {

        /*Bluetooth u açıyoruz.*/
        try {
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard //SerialPortService I
            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
            mmSocket.connect();
            mmOutputStream = mmSocket.getOutputStream();
            mmInputStream = mmSocket.getInputStream();
            beginListenForData();/*Bluetooth üzerinden gelen verileri yakalamak için bir listener oluşturuyoruz.*/
        } catch (Exception ignored) {
        }

    }

    void findBT() {
        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                MtxtVwState.setText("Bluetooth adaptörü bulunamadı");
            }
            if (BtAdapterSayac == 0) {
                if (!mBluetoothAdapter.isEnabled()) {
                    Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBluetooth, 0);
                    BtAdapterSayac = 1;
                }
            }
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    if (("HC-05").equals(device.getName().toString())) {/*Eşleşmiş cihazlarda HC-05 adında cihaz varsa bağlantıyı aktişleştiriyoruz. Burada HC-05 yerine bağlanmasını istediğiniz Bluetooth adını yazabilirsiniz.*/
                        mmDevice = device;
                        MtxtVwState.setText("Bağlantı Kuruluyor");
                        connectionstate = true;
                    }
                }
            }
        } catch (Exception ignored) {
        }
    }

    void closeBT() throws IOException {
        try {
            /*Aktif olan bluetooth bağlantımızı kapatıyoruz.*/
            if (mBluetoothAdapter.isEnabled()) {
                stopWorker = true;
                mBluetoothAdapter.disable();
                mmOutputStream.close();
                mmInputStream.close();
                mmSocket.close();
            } else {
            }
        } catch (Exception ignored) {
        }
    }

    void sendData(String data) throws IOException {
        try {
            if (connectionstate) {
                /*Bluetooth bağlantımız aktifse veri gönderiyoruz.*/
                mmOutputStream.write(data.getBytes());
            }
        } catch (Exception ignored) {
        }
    }

    void beginListenForData() {
        try {
            final Handler handler = new Handler();
            final byte delimiter = 10; //This is the ASCII code for a newline character

            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];
            workerThread = new Thread(new Runnable() {
                public void run() {
                    while (!Thread.currentThread().isInterrupted() && !stopWorker) {
                        try {
                            int bytesAvailable = mmInputStream.available();
                            if (bytesAvailable > 0) {
                                byte[] packetBytes = new byte[bytesAvailable];
                                mmInputStream.read(packetBytes);
                                for (int i = 0; i < bytesAvailable; i++) {
                                    byte b = packetBytes[i];
                                    if (b == delimiter) {
                                        final byte[] encodedBytes = new byte[readBufferPosition];
                                        System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                        final String data = new String(readBuffer, "US-ASCII");
                                        readBufferPosition = 0;

                                        handler.post(new Runnable() {
                                            public void run() {
                                                sGelenVeri = data.toString();
                                                sGelenVeri = sGelenVeri.substring(0, 3);
                                                MtxtVwState.setText(sGelenVeri);
                                            }
                                        });
                                    } else {
                                        readBuffer[readBufferPosition++] = b;
                                    }
                                }
                            }
                        } catch (IOException ex) {
                            stopWorker = true;
                        }
                    }
                }
            });
            workerThread.start();
        } catch (Exception ignored) {
        }
    }

}

