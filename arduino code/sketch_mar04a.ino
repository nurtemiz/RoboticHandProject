 //                == MASTER CODE ==
 //
#define ledPin 9
#include <Servo.h>
int state = 0;
int potValue = 0;
Servo thumb_Servo;
Servo index_Servo;
Servo middle_Servo;
Servo ring_Servo;
Servo pinky_Servo;
void setup() {
  thumb_Servo.attach(3);
  index_Servo.attach(4);
  middle_Servo.attach(5);
  ring_Servo.attach(6);
  pinky_Servo.attach(7);
  pinMode(ledPin, OUTPUT);
  digitalWrite(ledPin, LOW);
  Serial.begin(38400); // Default communication rate of the Bluetooth module
}
void loop() {
 
  Serial.println(state);
 if(Serial.available() > 0){ // Checks whether data is comming from the serial port
    state = Serial.read(); // Reads the data from the serial port
 }
 // Controlling the LED
 if (state == '1') {
  digitalWrite(ledPin, HIGH); // LED ON

 }
    if (state == '0') {
  digitalWrite(ledPin, LOW); // LED ON
 }
     if (state == 'a') {
  thumb_Servo.write(0);
 }
     if (state == 'b') {
  thumb_Servo.write(20);
 }
     if (state == 'c') {
  thumb_Servo.write(40);
 }
     if (state == 'd') {
  thumb_Servo.write(60);
 }
     if (state == 'e') {
  thumb_Servo.write(80);
 }
     if (state == 'f') {
  thumb_Servo.write(100);
 }
     if (state == 'g') {
  thumb_Servo.write(120);
 }
     if (state == 'h') {
  thumb_Servo.write(140);
 }
     if (state == 'i') {
  thumb_Servo.write(160);
 }
     if (state == 'j') {
  thumb_Servo.write(179);
 }

    if (state == '2') {
  index_Servo.write(0);
 }
     if (state == '3') {
  index_Servo.write(20);
 }
     if (state == '4') {
  index_Servo.write(40);
 }
     if (state == '5') {
  index_Servo.write(60);
 }
     if (state == '6') {
  index_Servo.write(80);
 }
     if (state == '7') {
  index_Servo.write(100);
 }
     if (state == '8') {
  index_Servo.write(120);
 }
     if (state == '9') {
  index_Servo.write(140);
 }
     if (state == 'l') {
  index_Servo.write(160);
 }
     if (state == 'm') {
  index_Servo.write(179);
 }
 
     if (state == 'n') {
  middle_Servo.write(0);
 }
     if (state == 'o') {
  middle_Servo.write(20);
 }
     if (state == 'p') {
  middle_Servo.write(40);
 }
     if (state == 'r') {
  middle_Servo.write(60);
 }
     if (state == 's') {
  middle_Servo.write(80);
 }
     if (state == 't') {
  middle_Servo.write(100);
 }
     if (state == 'u') {
  middle_Servo.write(120);
 }
     if (state == 'y') {
  middle_Servo.write(140);
 }
     if (state == 'v') {
  middle_Servo.write(160);
 }
     if (state == 'z') {
  middle_Servo.write(179);
 }
 
 
     if (state == ' ') {
  ring_Servo.write(0);
 }
     if (state == '!') {
  ring_Servo.write(20);
 }
     if (state == '%') {
  ring_Servo.write(40);
 }
     if (state == '#') {
  ring_Servo.write(60);
 }
     if (state == 'Z') {
  ring_Servo.write(80);
 }
     if (state == '(') {
  ring_Servo.write(100);
 }
     if (state == ')') {
  ring_Servo.write(120);
 }
     if (state == '*') {
  ring_Servo.write(140);
 }
     if (state == '+') {
  ring_Servo.write(160);
 }
     if (state == ',') {
  ring_Servo.write(179);
 }
 
 
     if (state == 'L') {
  pinky_Servo.write(0);
 }
     if (state == 'K') {
  pinky_Servo.write(20);
 }
     if (state == 'I') {
  pinky_Servo.write(40);
 }
     if (state == 'H') {
  pinky_Servo.write(60);
 }
     if (state == 'F') {
  pinky_Servo.write(80);
 }
     if (state == 'E') {
  pinky_Servo.write(100);
 }
     if (state == 'D') {
  pinky_Servo.write(120);
 }
     if (state == 'C') {
  pinky_Servo.write(140);
 }
     if (state == 'B') {
  pinky_Servo.write(160);
 }
     if (state == 'A') {
  pinky_Servo.write(179);
 }
 
 
// // Reading the potentiometer
// potValue = analogRead(A0);
//// Serial.println(potValue);
// int potvaluemapped = map(potValue, 0, 1023, 0, 179);
// if(potvaluemapped<=3){
//  Serial.write("0");
// }
// if(4<=potvaluemapped && potvaluemapped<=14){
//  Serial.write("10");
// }
// if(15<=potvaluemapped && potvaluemapped<=24){
//  Serial.write("20");
// }
// if(25<=potvaluemapped && potvaluemapped<=34){
//  Serial.write("30");
// }
// if(35<=potvaluemapped && potvaluemapped<=44){
//  Serial.write("40");
// }
// if(45<=potvaluemapped && potvaluemapped<=54){
//  Serial.write("50");
// }
// if(55<=potvaluemapped && potvaluemapped<=64){
//  Serial.write("60");
// }
// if(65<=potvaluemapped && potvaluemapped<=74){
//  Serial.write("70");
// }
// if(75<=potvaluemapped && potvaluemapped<=84){
//  Serial.write("80");
// }
// if(85<=potvaluemapped && potvaluemapped<=94){
//  Serial.write("90");
// }
// if(95<=potvaluemapped && potvaluemapped<=104){
//  Serial.write("100");
// }
// 
// Serial.println(potValueMapped);
// Serial.write(potValueMapped); // Sends potValue to servo motor
// delay(100);
// Serial.write("10"); // Sends potValue to servo motor
// delay(100);
// Serial.write("15"); // Sends potValue to servo motor
// delay(100);
// Serial.write("20"); // Sends potValue to servo motor
// delay(100);
// Serial.write("25"); // Sends potValue to servo motor
// delay(100);
// Serial.write("30"); // Sends potValue to servo motor
// delay(100);
// Serial.write("35"); // Sends potValue to servo motor
// delay(100);
// Serial.write("40"); // Sends potValue to servo motor
// delay(100);
// Serial.write("45"); // Sends potValue to servo motor
// delay(100);
// Serial.write("50"); // Sends potValue to servo motor
// delay(100);
// Serial.write("55"); // Sends potValue to servo motor
// delay(100);
// Serial.write("60"); // Sends potValue to servo motor
// delay(100);
// Serial.write("65"); // Sends potValue to servo motor
// delay(100);
// Serial.write("70"); // Sends potValue to servo motor
// delay(100);
// Serial.write("75"); // Sends potValue to servo motor
// delay(100);
// Serial.write("80"); // Sends potValue to servo motor
// delay(100);
// Serial.write("90"); // Sends potValue to servo motor
// delay(100);
// Serial.write("180"); // Sends potValue to servo motor
// delay(1000);
// Serial.write("0"); // Sends potValue to servo motor
// delay(2000);
}
