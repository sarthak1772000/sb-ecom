package com.ecommerce.sb_ecom.controller;

import com.ecommerce.sb_ecom.model.User;
import com.ecommerce.sb_ecom.payload.AddressDTO;
import com.ecommerce.sb_ecom.service.AddressService;
import com.ecommerce.sb_ecom.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    AuthUtil authUtil;

    @Autowired
    AddressService addressService;

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO)
    {
        User user = authUtil.loggedInUser();
        AddressDTO savedAddressDTO = addressService.createAddress(addressDTO, user);

        return new ResponseEntity<>(savedAddressDTO, HttpStatus.CREATED);
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAddresses(){

        List<AddressDTO> addressDTOS = addressService.getAllAddresses();

        return new ResponseEntity<List<AddressDTO>>(addressDTOS, HttpStatus.FOUND);
    }

    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long addressId){

        AddressDTO addressDTO = addressService.getAddressesById(addressId);

        return new ResponseEntity<AddressDTO>(addressDTO, HttpStatus.FOUND);
    }

    @GetMapping("/users/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddresses(){
        User user = authUtil.loggedInUser();
        List<AddressDTO> addressDTOS = addressService.getUserAddresses(user);

        return new ResponseEntity<List<AddressDTO>>(addressDTOS, HttpStatus.FOUND);
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> updateAddressById(@PathVariable Long addressId,
                                                        @RequestBody AddressDTO addressDTO){

        AddressDTO updatedAddress = addressService.updateAddress(addressId, addressDTO);

        return new ResponseEntity<AddressDTO>(updatedAddress, HttpStatus.FOUND);
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long addressId){

        String status = addressService.deleteAddress(addressId);

        return new ResponseEntity<String>(status, HttpStatus.OK);
    }
}
