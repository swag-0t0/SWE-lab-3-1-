

interface TV {
    boolean isEnabled();
     void enable();
     void disable();
     int getVolume();
    void setVolume(int volu);
     int getChannel();
    void setChannel(int channel);
}
class GeneralTV implements TV {  //basic functionality asteche  from TV


    int volume=9, channel=10;  
    boolean powerOn = false;

    @Override
    public boolean isEnabled()  
    {
     return powerOn;
    }
    @Override
    public void enable() 
    {
    powerOn = true;
    }
    @Override
    public void disable() 
    {
    powerOn = false;
    }
    @Override
    public int getVolume() 
    {
    return volume;
    }
    @Override
    public void setVolume(int vol) 
    {
        volume = vol;
    }
    @Override
    public int getChannel(){
     return channel;
    }
    @Override
    public void setChannel(int channel){
     this.channel = channel;
    }
}
class SmartTV implements TV {    //basic functionality asteche  from TV
    int volume = 15,channel = 10;
    boolean powerOn = false;
   

    @Override
    public boolean isEnabled(){
        return powerOn;
    }
    @Override
    public void enable(){
    powerOn = true;
    }
    @Override
    public void disable(){
        powerOn = false;
    }
    @Override
    public int getVolume(){
        return volume;
    }
    @Override
    public void setVolume(int volu){
        volume = volu;
    }

    @Override
    public int getChannel(){ 
     return channel;
    }

    @Override
    public void setChannel(int channel){
    this.channel = channel;
    }

    public void Youtube(YoutubeClass yt) 
    {
        System.out.println("SmartTV-Youtube");
        yt.runYoutube();
    }
}
class Remote {
    protected TV tv;
    Remote() 
    {
        tv = null;
    }
    Remote(TV tv){
        this.tv = tv;
    }
    public void togglePower() {
    if (tv.isEnabled()){
          tv.disable();
            System.out.println("Power-off");
      } else {
         tv.enable();
            System.out.println("Power-on");
        }
    }
    public void volumeUp(){
       if (tv.isEnabled()){
              tv.setVolume(tv.getVolume() + 4);
            System.out.println("volume increased amount 4");
       } 
    }
    public void volumeDown()
     {
        if (tv.isEnabled()){
            tv.setVolume(tv.getVolume() - 3);
            System.out.println("volumn decreased amount 3");
        } 
    }
    public void channelUp() 
    {
        if (tv.isEnabled()){
            tv.setChannel(tv.getVolume() + 1);
            System.out.println("Channel changed +1");
        } 
    }
    public void channelDown(){
        if (tv.isEnabled()){
            tv.setChannel(tv.getVolume() - 1);
            System.out.println("Channel changed -1");
        }
    }
}


class SmartRemote extends Remote {
    SmartRemote() {}
    SmartRemote(TV tv) 
    {
     super(tv);
    }
    void showYoutube(YoutubeClass youtube) {
        ((SmartTV) tv).Youtube(youtube);
    }
}





interface YoutubeClass{                 /*it is designed to implement the proxy design pattern */
    void runYoutube();                     /*second time call korle youtube start kora lagbe na */
}
class FirstLoadYoutube implements YoutubeClass{
    @Override
    public void runYoutube(){
        System.out.println("Youtube is running!!");
    }
}



class proxyLoadYoutube implements YoutubeClass {


    private FirstLoadYoutube firstLoadYoutube;

    @Override
    public void runYoutube(){

        if (firstLoadYoutube== null){

        firstLoadYoutube =new FirstLoadYoutube();

         System.out.println("Starting Youtube");
        }
          

        firstLoadYoutube.runYoutube();
    }
}

public class Main {

    public static void main(String[] args) {

        

        GeneralTV general_tv= new GeneralTV();//instances of general TV

        Remote general_remote=new Remote( general_tv);
        System.out.println("General_Tv--->");

        general_remote.togglePower();
       general_remote.volumeUp();
        general_remote.channelUp();
        general_remote.channelDown();
         general_remote.volumeDown();

        
        System.out.println();       
        
        System.out.println("Smart_Tv-->");      //instance of smart TV
        SmartTV smarttv=new SmartTV();
        SmartRemote smart_remote=new SmartRemote(smarttv);

         smart_remote.togglePower();
        smart_remote.volumeUp();
         smart_remote.channelUp();
         smart_remote.channelDown();
         smart_remote.volumeDown();

        System.out.println();
   
        YoutubeClass youtube=new proxyLoadYoutube();

        smart_remote.showYoutube(youtube);  //youtube() has been accessing through smartremote 

         smart_remote.showYoutube(youtube); //In this call youtube will not start again
       
    }

}