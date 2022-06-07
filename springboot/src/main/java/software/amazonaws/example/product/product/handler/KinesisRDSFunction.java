// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package software.amazonaws.example.product.product.handler;

import com.amazonaws.services.lambda.runtime.events.KinesisEvent;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazonaws.example.product.product.entity.Customer;
import software.amazonaws.example.product.product.Service;

import java.util.function.Function;

@Component
public class KinesisRDSFunction implements Function<KinesisEvent,Void> {

  ObjectMapper objectMapper;
  Service service;


  @Autowired
  public void setService(Service service) {
    this.service = service;
  }

  @Autowired
  public void setObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }


  @Override
  public Void apply(KinesisEvent event) {
    for(KinesisEvent.KinesisEventRecord rec : event.getRecords()) {
      System.out.println(new String(rec.getKinesis().getData().array()));
      service.save(new Customer("Soyer ",new String(rec.getKinesis().getData().array())));
    }
    return null;
  }


}
