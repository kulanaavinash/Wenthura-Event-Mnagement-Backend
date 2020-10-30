package com.crud.crudassignmentsb.service;

import com.crud.crudassignmentsb.dto.ServiceDto;
import com.crud.crudassignmentsb.modal.ImageModel;

import java.util.List;
import java.util.Optional;

public interface Services {


    public List<com.crud.crudassignmentsb.modal.Services> getAllServices();

    //    public String addService(com.crud.crudassignmentsb.modal.Services servicesData);
    String addService(ImageModel img, String ser_name, String ser_desc, String pac_name, String ser_status, String ser_offer);

    //    public String updateServices(com.crud.crudassignmentsb.modal.Services newServicesData);
    public String updateServices(ImageModel img, String ser_id, String ser_name, String ser_desc, String pac_name, String ser_status, String ser_offer);

    public String deleteServices(int id);

    public ServiceDto getServicesById(int id);

    public List<com.crud.crudassignmentsb.modal.Services> getServicesByPackage(String pacName);

//    public ImageModel saveImage(ImageModel imageModel);
//
//    public Optional<ImageModel> getImage(int imgId);

}
