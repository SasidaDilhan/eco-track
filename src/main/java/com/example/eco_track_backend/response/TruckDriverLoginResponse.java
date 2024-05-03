package com.example.eco_track_backend.response;

import com.example.eco_track_backend.model.TruckDriver;
import com.example.eco_track_backend.model.User;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class TruckDriverLoginResponse {

        private String token;
        private TruckDriver truckDriver;

}
