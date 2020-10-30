package com.crud.crudassignmentsb.service;

import com.crud.crudassignmentsb.dto.ServiceDto;
import com.crud.crudassignmentsb.modal.ImageModel;
import com.crud.crudassignmentsb.repository.ImageRepository;
import com.crud.crudassignmentsb.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesImpl implements Services {

    @Autowired
    private ServicesRepository ServicesRepositories;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<com.crud.crudassignmentsb.modal.Services> getAllServices() {
        List<com.crud.crudassignmentsb.modal.Services> servicesList = ServicesRepositories.findAll();
        return servicesList;
    }

//    @Override
//    public String addService(com.crud.crudassignmentsb.modal.Services serviceData) {
//        serviceData.setSerId(0);
//        ServicesRepositories.save(serviceData);
//        return "Save Data";
//    }
    @Override
    public String addService(ImageModel img, String ser_name, String ser_desc, String pac_name, String ser_status, String ser_offer) {
        com.crud.crudassignmentsb.modal.Services service = new com.crud.crudassignmentsb.modal.Services();
        service.setSerName(ser_name);
        service.setSerDesc(ser_desc);
        service.setPacName(pac_name);
        service.setSerOffer(new BigDecimal(ser_offer));
        service.setSerStatus(ser_status);
        service.setPicByte(img.getPicByte());
        service.setType(img.getType());
        ServicesRepositories.save(service);
        return "SUCCESS";
    }

//    @Override
//    public String updateServices(com.crud.crudassignmentsb.modal.Services newServicesData) {
//        String msg = "error";
//        if (newServicesData.getSerId() != 0) {
//            ServicesRepositories.save(newServicesData);
//            msg = "Data Update";
//        }
//        return msg;
//    }
    @Override
    public String updateServices(ImageModel img, String ser_id, String ser_name, String ser_desc, String pac_name, String ser_status, String ser_offer) {
        String msg = "ERROR";
        com.crud.crudassignmentsb.modal.Services service = new com.crud.crudassignmentsb.modal.Services();

        if (Integer.parseInt(ser_id) != 0) {
            service.setSerId(Integer.parseInt(ser_id));
            service.setSerName(ser_name);
            service.setSerDesc(ser_desc);
            service.setPacName(pac_name);
            service.setSerOffer(new BigDecimal(ser_offer));
            service.setSerStatus(ser_status);
            service.setPicByte(img.getPicByte());
            service.setType(img.getType());
            ServicesRepositories.save(service);
            msg = "SUCCESS";
        }
        return msg;
    }

    @Override
    public String deleteServices(int id) {
        ServicesRepositories.deleteById(id);
        return "SUCCESS";
    }

    @Override
    public ServiceDto getServicesById(int id) {
        com.crud.crudassignmentsb.modal.Services service = ServicesRepositories.getOne(id);
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setSerId(service.getSerId());
//        serviceDto.setPacId(service.getPackages().getPakId());
        serviceDto.setSerName(service.getSerName());
        serviceDto.setSerDesc(service.getSerDesc());
        serviceDto.setPacName(service.getPacName());
        serviceDto.setSerStatus(service.getSerStatus());
        serviceDto.setSerOffer(service.getSerOffer().toString());
        return serviceDto;
    }

    @Override
    public List<com.crud.crudassignmentsb.modal.Services> getServicesByPackage(String pacName) {
        List<com.crud.crudassignmentsb.modal.Services> servicesList = ServicesRepositories.getServicesByPacName(pacName);
        return servicesList;
    }

//    @Override
//    public ImageModel saveImage(ImageModel imageModel) {
//        return imageRepository.save(imageModel);
//    }
//
//    @Override
//    public Optional<ImageModel> getImage(int imgId) {
//        return imageRepository.findById(imgId);
//    }

}
