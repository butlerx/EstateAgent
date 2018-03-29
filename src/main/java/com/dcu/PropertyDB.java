package com.dcu;

import java.util.List;

public interface PropertyDB {
  List<Property> getAll();

  Property get(int id);

  void update(Property prop, int id);

  void delete(int id);

  int add(Property prop);
}
