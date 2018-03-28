package com.dcu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class PropertyDBImpl implements PropertyDB {
  private final List<Property> properties = new ArrayList<Property>();
  private final String[] types = {"apartment", "house"};

  public PropertyDBImpl() {
    Random rand = new Random();
    for (int i = 0; i < 100; i++) {
      this.properties.add(
          new Property(
              types[rand.nextInt(types.length - 1)],
              rand.nextInt(24) + 1,
              rand.nextInt(4) + 1,
              100000 + (int) (rand.nextDouble() * ((1000000 - 100000) + 1)),
              rand.nextInt(14) + 1,
              i));
    }
  }

  public List<Property> getAll() {
    return properties;
  }

  public Property get(int id) {
    if (properties.size() < id) {
      throw new NotFound();
    }
    return properties.get(id);
  }

  public void update(Property prop, int id) {
    if (properties.size() < id) {
      throw new NotFound();
    }
    properties.set(id, prop);
  }

  public void delete(int id) {
    properties.remove(id);
  }

  public int add(Property prop) {
    Property newProp =
        new Property(
            prop.getType(),
            prop.getDistrict(),
            prop.getBedrooms(),
            prop.getPrice(),
            prop.getDayLeft(),
            properties.size());
    properties.add(newProp);
    return properties.size();
  }
}
