package com.example.nur.robotichandproject;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;


public class VoiceControlFragment extends Fragment {

    BluetoothAdapter mBluetoothAdapterV;
    BluetoothSocket mmSocketV;
    BluetoothDevice mmDeviceV;
    OutputStream mmOutputStreamV;
    InputStream mmInputStreamV;
    Thread workerThreadV;
    byte[] readBufferV;
    int readBufferPositionV;
    volatile boolean stopWorkerV;
    boolean connectionstateV = false;
    byte BtAdapterSayacV = 0;
    Button btOnV, btConnectV, btSettingsV, parmakOn, parmakOff;
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    TextView view_sound, MtxtVwStateV;
    ImageButton start_button;
    String sGelenVeriV, komut;
    boolean bisCheckedV = false;

    public VoiceControlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewVoiceControl = inflater.inflate(R.layout.fragment_voice_control, container, false);

        btOnV = (Button) viewVoiceControl.findViewById(R.id.btOnV);
        btConnectV = (Button) viewVoiceControl.findViewById(R.id.btConnectV);
        btSettingsV = (Button) viewVoiceControl.findViewById(R.id.btSettingsV);
        parmakOn = (Button) viewVoiceControl.findViewById(R.id.parmakOn);
        parmakOff = (Button) viewVoiceControl.findViewById(R.id.parmakOff);
        MtxtVwStateV = (TextView) viewVoiceControl.findViewById(R.id.MtxtVwStateV);

        btOnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bisCheckedV) {
                    try {
                        openBT();
                        findBT();
                        MtxtVwStateV.setText("Bluetooth Açıldı");
                        bisCheckedV = true;
                        btOnV.setHint("Bluetooth Kapat");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        closeBT();
                        MtxtVwStateV.setText("Bluetooth Kapandı");
                        bisCheckedV = false;
                        btOnV.setHint("Bluetooth Aç");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btConnectV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bisCheckedV != false) {
                    try {
                        openBT();
                        findBT();
                        MtxtVwStateV.setText("Bağlantı Kuruldu");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else
                    Toast.makeText(getActivity(), "Bağlantı kurmak için Bluetooth'u etkinleştiriniz. Lütfen!", Toast.LENGTH_LONG).show();
            }
        });

        btSettingsV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btsettings = new Intent(getActivity(), btsettings.class);
                getActivity().startActivity(btsettings);
            }
        });

        view_sound = (TextView) viewVoiceControl.findViewById(R.id.viewSound);
        start_button = (ImageButton) viewVoiceControl.findViewById(R.id.btnStart);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startVoiceInput();
            }
        });

        parmakOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(komut.equals("başparmak") || komut.equals("baş parmak") || komut.equals("başparmağı")) {
                    try {
                        sendData("a");
                    } catch (Exception ignored) {
                    }
                }else if (komut.equals("işaretparmak") || komut.equals("işaret parmak") || komut.equals("işaret parmağı")) {
                    try {
                        sendData("2");
                    } catch (Exception ignored) {
                    }
                }else if (komut.equals("ortaparmak") || komut.equals("orta parmak")) {
                    try {
                        sendData("n");
                    } catch (Exception ignored) {
                    }
                }else if (komut.equals("yüzükparmağı") || komut.equals("yüzük parmak") || komut.equals("yüzük parmağı")) {
                    try {
                        sendData(" ");
                    } catch (Exception ignored) {
                    }
                }else if (komut.equals("serçeparmağı") || komut.equals("serçe parmak") || komut.equals("serçe parmağı")) {
                    try {
                        sendData("L");
                    } catch (Exception ignored) {
                    }
                }else
                    Toast.makeText(getActivity(), "Geçerli bir komut veriniz. Lütfen!", Toast.LENGTH_LONG).show();
            }
        });

        parmakOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(komut.equals("başparmak") || komut.equals("baş parmak") || komut.equals("başparmağı")) {
                    try {
                        sendData("e");
                    } catch (Exception ignored) {
                    }
                }else if (komut.equals("işaretparmak") || komut.equals("işaret parmak") || komut.equals("işaret parmağı")) {
                    try {
                        sendData("7");
                    } catch (Exception ignored) {
                    }
                }else if (komut.equals("ortaparmak") || komut.equals("orta parmak")) {
                    try {
                        sendData("t");
                    } catch (Exception ignored) {
                    }
                }else if (komut.equals("yüzükparmağı") || komut.equals("yüzük parmak") || komut.equals("yüzük parmağı")) {
                    try {
                        sendData(")");
                    } catch (Exception ignored) {
                    }
                }else if (komut.equals("serçeparmağı") || komut.equals("serçe parmak") || komut.equals("serçe parmağı")) {
                    try {
                        sendData("D");
                    } catch (Exception ignored) {
                    }
                }else
                    Toast.makeText(getActivity(), "Geçerli bir komut veriniz. Lütfen!", Toast.LENGTH_LONG).show();
            }
        });
        return viewVoiceControl;
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hareket ettirmek istediğiniz parmağı söyleyiniz!");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == -1 && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    view_sound.setText(result.get(0));
                    komut = view_sound.getText().toString();
                    return;
                }
                break;
            }
        }
    }

    /**********************************************************************************************
     * onCreate End
     *********************************************************************************************/
    void openBT() throws IOException {

        /*Bluetooth u açıyoruz.*/
        try {
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard //SerialPortService I
            mmSocketV = mmDeviceV.createRfcommSocketToServiceRecord(uuid);
            mmSocketV.connect();
            mmOutputStreamV = mmSocketV.getOutputStream();
            mmInputStreamV = mmSocketV.getInputStream();
            beginListenForData();/*Bluetooth üzerinden gelen verileri yakalamak için bir listener oluşturuyoruz.*/
        } catch (Exception ignored) {
        }

    }

    void findBT() {
        try {
            mBluetoothAdapterV = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapterV == null) {
                MtxtVwStateV.setText("Bluetooth adaptörü bulunamadı");
            }
            if (BtAdapterSayacV == 0) {
                if (!mBluetoothAdapterV.isEnabled()) {
                    Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBluetooth, 0);
                    BtAdapterSayacV = 1;
                }
            }
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapterV.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    if (("HC-05").equals(device.getName().toString())) {/*Eşleşmiş cihazlarda HC-05 adında cihaz varsa bağlantıyı aktişleştiriyoruz. Burada HC-05 yerine bağlanmasını istediğiniz Bluetooth adını yazabilirsiniz.*/
                        mmDeviceV = device;
                        MtxtVwStateV.setText("Bağlantı Kuruluyor");
                        connectionstateV = true;
                    }
                }
            }
        } catch (Exception ignored) {
        }
    }

    void closeBT() throws IOException {
        try {
            /*Aktif olan bluetooth bağlantımızı kapatıyoruz.*/
            if (mBluetoothAdapterV.isEnabled()) {
                stopWorkerV = true;
                mBluetoothAdapterV.disable();
                mmOutputStreamV.close();
                mmInputStreamV.close();
                mmSocketV.close();
            } else {
            }
        } catch (Exception ignored) {
        }
    }

    void sendData(String data) throws IOException {
        try {
            if (connectionstateV) {
                /*Bluetooth bağlantımız aktifse veri gönderiyoruz.*/
                mmOutputStreamV.write(data.getBytes());
            }
        } catch (Exception ignored) {
        }
    }

    void beginListenForData() {
        try {
            final Handler handler = new Handler();
            final byte delimiter = 10; //This is the ASCII code for a newline character

            stopWorkerV = false;
            readBufferPositionV = 0;
            readBufferV = new byte[1024];
            workerThreadV = new Thread(new Runnable() {
                public void run() {
                    while (!Thread.currentThread().isInterrupted() && !stopWorkerV) {
                        try {
                            int bytesAvailable = mmInputStreamV.available();
                            if (bytesAvailable > 0) {
                                byte[] packetBytes = new byte[bytesAvailable];
                                mmInputStreamV.read(packetBytes);
                                for (int i = 0; i < bytesAvailable; i++) {
                                    byte b = packetBytes[i];
                                    if (b == delimiter) {
                                        final byte[] encodedBytes = new byte[readBufferPositionV];
                                        System.arraycopy(readBufferV, 0, encodedBytes, 0, encodedBytes.length);
                                        final String data = new String(readBufferV, "US-ASCII");
                                        readBufferPositionV = 0;

                                        handler.post(new Runnable() {
                                            public void run() {
                                                sGelenVeriV = data.toString();
                                                sGelenVeriV = sGelenVeriV.substring(0, 3);
                                                MtxtVwStateV.setText(sGelenVeriV);
                                            }
                                        });
                                    } else {
                                        readBufferV[readBufferPositionV++] = b;
                                    }
                                }
                            }
                        } catch (IOException ex) {
                            stopWorkerV = true;
                        }
                    }
                }
            });
            workerThreadV.start();
        } catch (Exception ignored) {
        }
    }

}
