package uz.marokand.market.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.marokand.market.dto.ProductDto;
import uz.marokand.market.entity.Photo;
import uz.marokand.market.entity.Product;
import uz.marokand.market.exception.RecordNotFoundException;
import uz.marokand.market.service.PhotoService;
import uz.marokand.market.service.ProductService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private PhotoService photoService;
    @GetMapping("/product")
    public String customer(Model model){
        model.addAttribute("product",productService.getAllProduct());
        return "product/list-product";
    }

    //complete
    @GetMapping("/product/delete/{id}")
    public String delete(@PathVariable("id") Long id) throws RecordNotFoundException {
        productService.deleteProductById(id);
        return "redirect:/admin/product";
    };

    //    complete
    @RequestMapping(value = "/product/search",method = RequestMethod.POST)
    public String search(Model model,@RequestParam(name = "search") String search){
        List<Product> customer = productService.getProductByName(search);
        model.addAttribute("product",customer);
        return "product/list-product";
    }
    //    complete
    @GetMapping(path = {"/product/edit/{id}","/product/edit"})
    public String editCustomer(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        Product product=productService.getProductById(id.get());
        model.addAttribute("editProduct",product);
        return "product/add-edit-product";
    }
    @PostMapping("/product/edit")
    public String update(@Valid @ModelAttribute Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "product/add-edit-product";
        }
        productService.update(product);
        return "redirect:/admin/product";
    }
    //    complete
    @GetMapping("/product/add")
    public String editCustomer(Model model) {
        model.addAttribute("add",new ProductDto());
        return "product/add-product";
    }
    @PostMapping("/product/add")
    public String registration(@Valid @ModelAttribute("add") ProductDto productDto,
                               BindingResult bindingResult) throws NotFoundException, IOException {
        if (bindingResult.hasErrors()) {
            return "product/add-product";
        }
        productService.save(productDto);
        return "redirect:/admin/product";

    }
    @GetMapping("/product/view/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        Photo photo=photoService.getPhotoById(id.get());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(photo.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+photo.getName()+"\"")
                .body(new ByteArrayResource(photo.getPhotoBytes()));
    }

    @GetMapping(value = "/product/back")
    public String search(Model model){
        return "product/list-product";
    }

}
