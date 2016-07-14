package com.doysoft.q_radio;

public class Conthing {
    private String title;  
    private String Introduce;  
    private int picture;  
    private String imgUrl;
    private String  lregion;
    private String lname;
    private String lpic;
    private String url;
 //   private String category;
    
    public String geturl() {  
        return url;  
    }  
  
    public void setllink(String url) {  
        this.url = url;  
    }
    
    public String getlpic() {  
        return lpic;  
    }  
  
    public void setlpic(String lpic) {  
        this.lpic = lpic;  
    }
    
    public String getlname() {  
        return lname;  
    }  
  
    public void setlname(String lname) {  
        this.lname = lname;  
    }
    
    public String getlregion() {  
        return lregion;  
    }  
  
    public void setlregion(String lregion) {  
        this.lregion = lregion;  
    }
   // 
    public String getTitle() {  
        return title;  
    }  
  
    public void setTitle(String title) {  
        this.title = title;  
    }  
  
    public String getinfo() {  
        return Introduce;  
    }  
  
    public void setinfo(String info) {  
        Introduce = info;  
    }  
  
    public int getPicture() {  
        return picture;  
    }  
  
    public void setPicture(int picture) {  
        this.picture = picture;  
    }  
  
    
    /**
     * @param lpic 
     * @param lname 
     * @param lregion  
     *  
     */  
    public Conthing(String lregion, String lname, String lpic, String llink) {  
        super();  
        this.lregion = lregion;
        this.lname = lname;
        this.lpic = lpic;
       // this.llink = llink;
    }  
  
    
    /** 
     * @param title 
     * @param introduce 
     * @param picture 
     * @param url 
     */  
    public Conthing(String title, String introduce, int picture, String url) {  
        super();  
        this.title = title;  
        Introduce = introduce;  
        this.picture = picture;  
        this.url= url;
        
    }  
  
}  
