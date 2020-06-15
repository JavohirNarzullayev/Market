package uz.marokand.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.marokand.market.entity.Photo;
import uz.marokand.market.entity.Product;
import uz.marokand.market.exception.RecordNotFoundException;
import uz.marokand.market.repository.PhotoRepository;
import uz.marokand.market.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private ProductRepository productRepository;

    public Photo getPhotoById(Long aLong) throws RecordNotFoundException {
        Optional<Photo> photo = photoRepository.findById(aLong);
        if(photo.isPresent()) {
            return photo.get();
        } else {
            throw new RecordNotFoundException("No product record exist for given id");
        }
    }
    @Transactional
    public List<Product> getAllPhotos(){
        return productRepository.findAll();
        }
    }


