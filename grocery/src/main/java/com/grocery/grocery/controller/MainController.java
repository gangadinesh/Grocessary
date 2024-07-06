package com.grocery.grocery.controller;


import com.grocery.grocery.model.Grocery;
import com.grocery.grocery.model.Product;
import com.grocery.grocery.model.User;
import com.grocery.grocery.repository.GroceryRepo;
import com.grocery.grocery.repository.ProductRepo;
import com.grocery.grocery.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(){
        return "home";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/sign")
    public String sign(@RequestParam("email") String e,@RequestParam("password") String p){

        User o=userRepo.findByEmail(e);

        if(o==null && !(p.isEmpty()) && !(e.isEmpty())){
            User ob = new User(e,p);
            userRepo.save(ob);//INSERT INTO USER WHERE EMAIL=E,PASSWORD=P

            return "login";
        }
        else{
            return "signup";
        }

    }

    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/log")
    public String log(@RequestParam("email") String email, @RequestParam("password") String p,Model model){

      User u = userRepo.findByEmail(email);
      if(u.password.equals(p)){

          List<Product> o=productRepo.findAll();
          model.addAttribute("products",o);
          return "main";
      }
      else {
          return "login";
      }
    }

@GetMapping("/addproduct")
    public String addproduct(){
        return "add_product";
}

@Autowired
private GroceryRepo groceryRepo;

String upload_folder="C:/Users/RETECH-01/Desktop/grocery/grocery/src/main/resources/static";
@PostMapping("/add")
    public String add(@RequestParam("name") String name,@RequestParam("image") MultipartFile image) throws IOException {

    String image_name = image.getOriginalFilename();
        if(image_name!=null){

            File file=new File(upload_folder,image_name);
            image.transferTo(file);
            Grocery g=new Grocery(name,image_name);
            groceryRepo.save(g);
            return "add_product";
        }
        else{
            return "error";
        }

}

@GetMapping("/show")
    public String show(Model model){
    List<Grocery> o=groceryRepo.findAll();
    model.addAttribute("products",o);
    return "main";
}

}
