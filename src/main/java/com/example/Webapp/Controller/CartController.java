package com.example.Webapp.Controller;

import com.example.Webapp.Entity.CartDetailsEntity;
import com.example.Webapp.Repository.CartRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Transactional
@Controller
public class CartController {

    @Autowired
    private CartRepository cartRepo;

    // ✅ Add to cart
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam String servicename,@RequestParam int price,HttpSession session,HttpServletRequest request)
    {
    	String phone=(String)session.getAttribute("phonenumber");
    	if(phone!=null)
    	{
    		boolean exists = cartRepo.existsByPhonenumberAndServicenameAndPrice(phone, servicename, price);
    	if(!exists)
    	{
    	CartDetailsEntity cartItems=new CartDetailsEntity();
    	cartItems.setServicename(servicename);
    	cartItems.setPrice(price);
    	cartItems.setPhonenumber(phone);
    	cartRepo.save(cartItems);
    	}
    	
    	
    	return "redirect:/cart";
    	}
    	else {
            // 🟡 Save current URL in session (the referer)
            String referer = request.getHeader("Referer");
            session.setAttribute("redirectAfterLogin", referer);
            return "redirect:/login1";
        }
    	
    	
    	
    }
   
//    public String addToCart(@RequestParam String serviceName,
//                            @RequestParam int price,
//                            HttpSession session) {
//
//        String phone = (String) session.getAttribute("phonenumber");
//
//        if (phone != null) {
//            boolean exists = cartRepo.existsByPhonenumberAndServicenameAndPrice(phone, serviceName, price);
//            if (!exists) {
//                CartDetailsEntity cart = new CartDetailsEntity();
//                cart.setPhonenumber(phone);
//                cart.setServicename(serviceName);
//                cart.setPrice(price);
//                cartRepo.save(cart);
//            }
//        }
//
//        // ✅ Redirect back to services page (change to your actual page)
//        return "redirect:/cart";
//    }

    // ✅ Show cart page
    @GetMapping("/cart")
    
    public String showCart(Model model, HttpSession session) {
        String phone = (String) session.getAttribute("phonenumber");
       if(phone==null)
    	   return "redirect:/login1";
        if (phone != null) {
            List<CartDetailsEntity> cartItems = cartRepo.findByPhonenumber(phone); 
            int total=0;
            for(CartDetailsEntity count:cartItems) {
            	total+=count.getPrice();
            	System.out.println(count.getPrice());
            }

            model.addAttribute("cartItems", cartItems);
            model.addAttribute("cartCount", cartItems.size());
            model.addAttribute("totalamount",total);
        }
        return "cart"; // ✅ make sure cart.html exists in templates
    }

    // ✅ Remove item from cart
    @PostMapping("/removeFromCart")
    @Transactional
    public String removeFromCart(@RequestParam String serviceName,
                                 @RequestParam int price,
                                 HttpSession session) {
        String phone = (String) session.getAttribute("phonenumber");
        if (phone != null) {
            cartRepo.deleteByPhonenumberAndServicenameAndPrice(phone, serviceName, price);
        }
        return "redirect:/cart";
    }
    

}
