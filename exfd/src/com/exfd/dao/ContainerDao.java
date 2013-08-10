package com.exfd.dao;

import java.util.ArrayList;

import com.exfd.domain.Container;

public interface ContainerDao {
	void add(Container container);

	void delete(String code);

	void update(Container container);

	Container find(String code);

	ArrayList<Container> list();
}
