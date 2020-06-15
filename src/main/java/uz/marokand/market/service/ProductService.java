package uz.marokand.market.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.marokand.market.dto.ProductDto;
import uz.marokand.market.entity.Photo;
import uz.marokand.market.entity.Product;
import uz.marokand.market.exception.RecordNotFoundException;
import uz.marokand.market.repository.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Value("${upload.folder}")
    private String uploadFolder;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }
    @Transactional
    public List<Product> getAllProduct() {
        List<Product>products=productRepository.findAll();
        if (products.size()>0)return products;
        else return new ArrayList<>();
    }

    public void deleteProductById(Long id) throws RecordNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            System.out.println(product.get().getName());
            String fileWithoutExtention = product.get().getName();
            File file=new File(uploadFolder,String.format("/picture/%s.%s", fileWithoutExtention,"png"));
            if (file.delete()){
                productRepository.deleteById(id);
                System.out.println("Successfully remove photo!");
            }
        } else {
            throw new RecordNotFoundException("No product record exist for given id");
        }
    }

    @Transactional
    public List<Product> getProductByName(String name) {
        List<Product> products;
        if (name!= null && !name.isEmpty()){
            products =productRepository.findByNameContaining(name);
        }
        else {
            products =productRepository.findAll();
        }
        return products;
    }

    public Product getProductById(Long aLong) throws RecordNotFoundException {
        Optional<Product> employee = productRepository.findById(aLong);
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No product record exist for given id");
        }
    }

    @Transactional
    public void update(Product product) {
        Optional<Product> updateProduct = productRepository.findById(product.getId());
        if (updateProduct.isPresent()){
            Product product1= updateProduct.get();
            product1.setName(product.getName());
            product1.setCategory(product.getCategory());
            product1.setDescription(product.getDescription());
            product1.setPrice(product.getPrice());
            productRepository.save(product1);
        }
        else {
            productRepository.save(product);

        }
    }

    public void save(ProductDto productDto) throws IOException {
       String photoName=productDto.getMultipartFile().getOriginalFilename();
       String fileWithoutExtension=FilenameUtils.removeExtension(productDto.getName());
       Long fileSize=productDto.getMultipartFile().getSize();
       String contentType=productDto.getMultipartFile().getContentType();
       byte[] bytes=productDto.getMultipartFile().getBytes();

       Photo photo=new Photo(photoName,fileSize,contentType,bytes);
       photo.setUploadPath(String.format("/img/%s.%s",fileWithoutExtension,"png"));
       Product product=new Product(productDto.getName(),productDto.getDescription(),productDto.getPrice(),productDto.getCategory(),photo);
       productRepository.save(product);


        File uploadFolder=new File(String.format("%s/picture/",this.uploadFolder));
        if (!uploadFolder.exists()&& uploadFolder.mkdirs()){
            System.out.println("Fayl saqlandi");
        }else {
            System.out.println("Error");
        }
        uploadFolder=uploadFolder.getAbsoluteFile();

       File upload=new File(uploadFolder,String.format("%s.%s",fileWithoutExtension,"png"));
       try {
           productDto.getMultipartFile().transferTo(upload);

       }catch (IOException e){
           e.printStackTrace();
       }
    }

}
