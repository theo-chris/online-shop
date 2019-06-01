package eMarket;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import eMarket.domain.Role;
import eMarket.domain.Store;
import eMarket.repository.ProductRepository;
import eMarket.repository.RoleRepository;

@SpringBootApplication
public class EMarketApp extends WebMvcConfigurerAdapter implements CommandLineRunner {

	@Autowired ProductRepository productRepo;
	@Autowired RoleRepository roleRepo;
	
	private static Store store = new Store();
	private static LocalDate systemDate;
	
	public final static int ADMIN = 1;
	public final static int USER = 2;
	public final static int PREMIUM = 3;
	
	
    public static Store getStore() {
		return store;
	}

	public static void setStore(Store store) {
		EMarketApp.store = store;
	}

	public static LocalDate getSystemDate() {
		return systemDate;
	}

	public static void setSystemDate(LocalDate systemDate) {
		EMarketApp.systemDate = systemDate;
	}

	public static void main(String[] args) {
        SpringApplication.run(EMarketApp.class, args);
    }

    public void run(String... args) {
    		// initialize calendar
    	 	Calendar calendar = Calendar.getInstance();
    	 	calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
    	 	systemDate = calendar.getTime().toInstant().atZone(ZoneId.of("GMT")).toLocalDate();
    	 	
    	 	roleRepo.save(new Role(ADMIN,"ADMIN"));
    	 	roleRepo.save(new Role(USER,"USER"));
    	 	roleRepo.save(new Role(PREMIUM,"PREMIUM"));
//    	
//    		// PRODUCTS
//    		Product banana = new Product(0,"Banana","yellow",0.16);
//    		productRepo.save(banana);
////    		EMarketApp.getStore().getProductList().add(banana);
//    		Product orange = new Product(1,"Orange","Valencian",0.20);
////    		EMarketApp.getStore().getProductList().add(orange);
//    		productRepo.save(orange);
//    		Product apple = new Product(2,"Apple","Royal Gala",0.25);
////    		EMarketApp.getStore().getProductList().add(apple);
//    		productRepo.save(apple);
////    		EMarketApp.getStore().getProductList().add(new Product(3,"Grapes","Red",1.49));
//    		productRepo.save(new Product(3,"Grapes","Red",1.49));
//    		Product kiwi = new Product(4,"Kiwi","Green",0.35);
////    		EMarketApp.getStore().getProductList().add(kiwi);
//    		productRepo.save(kiwi);
////    		Product.lastId = 5;
//    		
//    		// DEALS
//    		// bananas
//    		SimpleDateFormat isoFormat = new SimpleDateFormat("dd/MM/yyyy");
//    		isoFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
//    		String startDate = "02/08/2017";
//    		try {
//            LocalDate newDate = isoFormat.parse(startDate).toInstant().atZone(ZoneId.of("GMT")).toLocalDate();
//
//            Deal deal = new Deal(0,newDate,0.10,banana);
//            deal.close();
////            EMarketApp.getStore().getDealList().add(deal);
//            dealRepo.save(deal);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    		// oranges
//		LocalDate today = getSystemDate();
//        Deal deal = new Deal(1,today,0.20,orange);
//        deal.close();
////        EMarketApp.getStore().getDealList().add(deal);
//        dealRepo.save(deal);
//        // kiwis
//        try {
//	        LocalDate date1 = isoFormat.parse("01/06/2018").toInstant().atZone(ZoneId.of("GMT")).toLocalDate();
////	last change        deal = new Deal(2,date1,0.20,kiwi);
//	        deal = new Deal(2,date1,0.20,apple);
////	        EMarketApp.getStore().getDealList().add(deal);
//	        dealRepo.save(deal);
//	        
//	        date1 = isoFormat.parse("01/01/1965").toInstant().atZone(ZoneId.of("GMT")).toLocalDate();
//	        deal = new Deal(3,date1,0.20,kiwi);
//	        date1 = isoFormat.parse("05/01/1965").toInstant().atZone(ZoneId.of("GMT")).toLocalDate();
//	        deal.setEndDate(date1);
////	        EMarketApp.getStore().getDealList().add(deal);
//	        dealRepo.save(deal);
//	        
//	        date1 = isoFormat.parse("02/01/1970").toInstant().atZone(ZoneId.of("GMT")).toLocalDate();
//	        deal = new Deal(4,date1,0.20,kiwi);
//	        date1 = isoFormat.parse("04/01/1970").toInstant().atZone(ZoneId.of("GMT")).toLocalDate();
//	        deal.setEndDate(date1);
////	        EMarketApp.getStore().getDealList().add(deal);
//	        dealRepo.save(deal);
//	        
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//    		Deal.lastId = 5;
    		
    }
}
