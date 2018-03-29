package com.dcu;

import java.util.List;

public interface PropertyDB {
  List<Property> getAll();

  Property get(final int id);

  void update(final Property prop, final int id);

  void delete(final int id);

  int add(final Property prop);
}
