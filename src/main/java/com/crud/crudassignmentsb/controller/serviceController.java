package com.crud.crudassignmentsb.controller;

import com.crud.crudassignmentsb.dto.ServiceDto;
import com.crud.crudassignmentsb.modal.ImageModel;
import com.crud.crudassignmentsb.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class serviceController {

    @Autowired
    private Services services;

    @GetMapping(path = "/load")
    public String mainRequest() {
        return "Hello login thimira";
    }

    @GetMapping("/all")
    public List<com.crud.crudassignmentsb.modal.Services> getAllServices() {
        return services.getAllServices();
    }

    //    @PostMapping("/add")
//    public String addService(@RequestBody com.crud.crudassignmentsb.modal.Services serviceData) {
//        return services.addService(serviceData);
//    }
    @PostMapping("/add")
    public ResponseEntity<Void> addService(@RequestPart("imageFile") MultipartFile file,
                             @RequestParam("ser_name") String ser_name,
                             @RequestParam("ser_desc") String ser_desc,
                             @RequestParam("pac_name") String pac_name,
                             @RequestParam("ser_status") String ser_status,
                             @RequestParam("ser_offer") String ser_offer
    )  throws IOException {

        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        String responce = services.addService(img, ser_name, ser_desc, pac_name, ser_status, ser_offer);
        if(responce=="SUCCESS"){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

//    @PutMapping("/update")
//    public String updateService(@RequestBody com.crud.crudassignmentsb.modal.Services newServiceData) {
//        return services.updateServices(newServiceData);
//    }
    @PutMapping("/update")
    public ResponseEntity<Void> updateService(@RequestPart("imageFile") MultipartFile file,
                                @RequestParam("ser_id") String ser_id,
                                @RequestParam("ser_name") String ser_name,
                                @RequestParam("ser_desc") String ser_desc,
                                @RequestParam("pac_name") String pac_name,
                                @RequestParam("ser_status") String ser_status,
                                @RequestParam("ser_offer") String ser_offer) throws IOException{
        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        String responce = services.updateServices(img, ser_id, ser_name, ser_desc, pac_name, ser_status, ser_offer);
        if(responce=="SUCCESS"){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteService(@RequestParam Integer id) {
        String responce = services.deleteServices(id);
        if(responce=="SUCCESS"){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/find")
    @ResponseBody
    public ServiceDto findService(@RequestParam Integer id) {
        ServiceDto serviceDto = services.getServicesById(id);
        return serviceDto;
    }
//    @GetMapping("/find")
//    public com.crud.crudassignmentsb.modal.Services findService(@RequestParam Integer id) {
//        com.crud.crudassignmentsb.modal.Services serviceDto = services.getServicesById(id);
//        serviceDto.setPicByte(decompressBytes(serviceDto.getPicByte()));
//        return serviceDto;
//    }

    @GetMapping("/package")
    public List<com.crud.crudassignmentsb.modal.Services> getServicesByPackage(@RequestParam String pacName) {
        List<com.crud.crudassignmentsb.modal.Services> serviceList = services.getServicesByPackage(pacName);
        for (com.crud.crudassignmentsb.modal.Services s:serviceList) {
            s.setPicByte(decompressBytes(s.getPicByte()));
        }
        return serviceList;
    }

//    @PostMapping("/uploadImage")
//    public String getUploadImage(@RequestPart("imageFile") MultipartFile file) throws IOException {
////        System.out.println("Original Image Byte Size - " + file.getBytes().length);
//        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
//                compressBytes(file.getBytes()));
//
//        ImageModel imgmod = services.saveImage(img);
//        return (imgmod != null) ? "SUCCESS" : "ERROR";
//    }
//
//    @GetMapping("/getImage")
//    public ImageModel getImage(@RequestParam Integer id) throws IOException {
//        final Optional<ImageModel> retrievedImage = services.getImage(id);
//        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
//                decompressBytes(retrievedImage.get().getPicByte()));
//        return img;
//    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
