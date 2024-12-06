package model;

public class SingletonModel {
   
    private static NoticeList instanceNotice;
    private static User instanceUser;

    private SingletonModel() {        
    }
    
    public static NoticeList getInstanceNotice() {

        if(instanceNotice == null) {
            instanceNotice = new NoticeList();
            try{            
                instanceNotice.addNotice( new Notice("Initial Notice", "User1", Type.OTHER, "Initial content"));
                
            } catch(MyThrownException ex) {
            }
        }
        return instanceNotice;
    }   
    
    public static User getInstanceUser(){
        if(instanceUser == null) {
            instanceUser = new User();
            
        }
        return instanceUser;
    }
}
