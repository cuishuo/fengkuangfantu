package com.example.fengkuangfantu.common;

import android.os.Environment;

public interface AppConstant {
    
/**
 * number from -1 to 9 
 * */
	int NO_0 = 0;
	int NO_1 = 1;
	int NO_2 = 2;
	int NO_3 = 3;
	int NO_4 = 4;
	int NO_5 = 5;
	int NO_6 = 6;
	int NO_7 = 7;
	int NO_8 = 8;
	int NO_9 = 9;
	int NO_10 = 10;
	int NO_WAP_30 = 30;
	int NO_WAP_50 = 50;
	int NO_ERROR = -1;
	int NO_END = -1;

/**
 * seconds count
 * */	
	int SECOND_HALF= 500;
	int SECOND_1 = 1000;
	

/**
 * common character for reusing
 * */
	char CHARACTER_SLASH = '/';
	char CHARACTER_EQUAL = '=';
	char CHARACTER_AND = '&';
	char CHARACTER_QUESTION = '?';
	char CHARACTER_BARS = '-';
	
/**
 * buffering size
 * */
	int BUFFER_SIZE = 1024;
	
/**
 * the path of save images
 */
	String IMAGE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/HiveView/imagefiles";
/**
 * the max size of upload photos	
 */
	int MAX_WIDTH = 1200;
	int MAX_HEIGHT = 1200;
	
/**
 * mine detail item number
 */
	final int TRIPS = 0;
	final int SETTINGS = 1;
	final String Id = "id";
	final String Name = "name";
	final String NameCn = "name_cn";
	final String Lat = "lat";
	final String Lng = "lng";
	final String Address = "address";
	final String Contact = "contact";
	final String Star = "star";
	final String Score = "score";
	final String ReviewCount = "reviewCount";
	final String State = "state";
	final String CityId = "cityId";
	final String author = "author";
	final String location = "location";
	final String type = "type";
	final String time = "time";
	final String Ctime = "cTime";
	final String score = "score";
	final String title = "title";
	final String pros = "pros";
	final String cons = "cons";
	final String comment = "comment";
	final String hotelCount = "hotelCount";
	final String restaurantCount = "restaurantCount";
	final String attractionCount = "attractionCount";
	final String APIKey = "Gtrs33uZTqQ0BxgaPFIFQlaS";
	final String cover = "cover";
	final String price = "price";
	final String info = "info";
	final String info_cn = "info_cn";
	final String price_low = "price_low";
	final String price_high = "price_high";
	
	//order
	final int DISTANCE = 0;
	final int SCROE = 1;
	final int PRICE = 2;
	final int REVIEW = 3;
	
	//order
	final int REVIEWDATE = 2;
	final int REVIEWSCROE = 3;
	final int REVIEWCHINESE = 4;
	
	
	//price
	final String PRICE_0 = "100";
	final String PRICE_1 = "150";
	final String PRICE_2 = "250";
	final String PRICE_3 = "400";
	final int Price_0 = 100;
	final int Price_1 = 150;
	final int Price_2 = 250;
	final int Price_3 = 400;
	final int Price_4 = 500;
//	final String PRICE_0 = "100";
//	final String PRICE_0 = "100";
//	final String PRICE_0 = "100";
//	final String PRICE_0 = "100";
//	final String PRICE_0 = "100";
	
	final int HOTEL_PAGE = 0;
	final int RESTAURANTS_PAGE = 1;
	final int ATTRACTIONS_PAGE = 2;
	final int SHOPPINGS_PAGE = 3;
	final int ACTIVITYS_PAGE = 4;
	final int RENTALS_PAGE = 5;
	final int DAY_PAGE = 6;
	final int COLLECT_PAGE = 7;
	
	final int CURRENTSEARCH = 0;
	final int DESTINATIONSEARCH = 1;
	
	final int TYPE_NORMAL = 0;
	final int TYPE_UNPLAN = 1;
	final int TYPE_DAY = 2;
	final int TYPE_BLANK = 3;
	
	final int FLAG_UNPLAN = 1;
	final int FLAG_PLAN = 2;
	
	final int EDIT_FLAG = 0;
	final int ADD_FLAG = 1;
	
	final String MODULE_HOTEL = "hotel";
	final String MODULE_RESTAURANT = "restaurant";
	final String MODULE_ATTRACTION = "attraction";
	final String MODULE_SHOPPING = "shopping";
	final String MODULE_ACTIVITY = "activity";
	final String MODULE_RENTAL = "rental";
	final String MODULE_COUNTRY = "country";
	final String MODULE_CITY = "city";
	final String MODULE_DAY = "day";
	final String MODULE_UNPLAN = "unplan";
	final String MODULE_PLAN_NORMAL = "plannormal";
	
	final String STATE_LOCAL = "local";
	final String STATE_UPDATE = "update";
	final String STATE_NET = "net";
	
	/**
	 * city type - America
	 */
	final int CITYTYPE_THAILAND = 0;
	final int CITYTYPE_AMERICA = 1;
	final int COUNTRYTYPE_FRANCE = 2;
	
	final String COUNTRY = "country";
	final String CITY = "city";
	
	//color
	final String TITLEBARTEXT = "#a3a3a3";
	final String COMMONGREEN = "#ff3ad2a2";
	final String COMMONLIGHTGREEN = "#553ad2a2";
	final String COMMONTRANSPARENTGREEN = "#003ad2a2";
	final String WHITE = "#ffffffff";
	final String WHITE0 = "#00ffffff";
	final String WHITE1 = "#55ffffff";
	final String WHITE2 = "#feffffff";
	final String WHITE3 = "#66ffffff";
	final String COMPANYBACK = "#f7f7f7";
	final String DETAILGRAY = "#898989";
	final String COLLECTLABELBACK = "#687b7c";
	final String REGISTERGRAY = "#A5A5A5";
	final String TRANSPARENT = "#00000000";
	final String ADDTRIPGRAY = "#f3f5f5f5";
	final String COMMONTITLE = "#434343";
	final String TRIPHINT = "#7d7d7d";
	final String TRIPTITLE = "#313131";
	final String RED = "#FF6666";
	final String TRIPCONTENTGRAY = "#898a89";
	final String COMMONGRAY = "#f4f4f4";
	final String STARTTEXTNOTCLICL = "#ff828282";
	
	//sort
	final String DISTANCESTRING = "distance";
	
	final int HOT_PAGE = 0;
	final int STATUS_PAGE = 1;
	final int SERVICE_PAGE = 2;
	final int PRICE_PAGE = 3;
	
	final int RATE_FLAG_FROM = 1;
	final int RATE_FLAG_TO = 2;
}
   
  
