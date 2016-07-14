package com.doysoft.q_radio;

public class Catething {

	private String catename;
	private int catepicture;
	private String cateregion;
	private String cateurl;
	private String category;
	
	public int getcatepicture() {  
        return catepicture;  
    }  
  
    public void setcatepicture(int catepicture) {  
        this.catepicture = catepicture;  
    }  
    public String getcategory() {  
        return category;  
    }  
  
    public void setcategory(String category) {  
        this.category = category;  
    }
	
    public String getcateurl() {  
        return cateurl;  
    }  
  
    public void setcateurl(String cateurl) {  
        this.cateurl = cateurl;  
    }
    
    public String getcateregion() {  
        return cateregion;  
    }  
  
    public void setcateregion(String cateregion) {  
        this.cateregion = cateregion;  
    }
    
    public String getcatename() {  
        return catename;  
    }  
  
    public void setcatename(String catename) {  
        this.catename = catename;  
    }
	
    
    /** 
     * @param catename 
     * @param category 
     * @param catepicture 
     * @param cateurl
     * @param cateregion
     */  
    public Catething(String catename, String cateregion , int catepicture, String cateurl, String category) {  
        super();  
        this.catename = catename;  
        this.category = category;
        this.catepicture = catepicture;  
        this.cateurl= cateurl;
       
        this.cateregion =cateregion;
    }  
    
    
}
