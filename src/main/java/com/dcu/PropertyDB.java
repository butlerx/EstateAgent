package com.dcu;

import java.util.List;

public interface PropertyDB {
  public List<Property> getAll();

  public Property get(int id);

  public void update(Property prop, int id);

  public void delete(int id);

  public int add(Property prop);
}
