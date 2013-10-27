package com.servlet.sort;

import java.util.Comparator;

import com.servlet.model.ProductTagMenuModel;

public class TagComparator implements Comparator<ProductTagMenuModel> {

	@Override
	public int compare(ProductTagMenuModel productTagMenuModel1, ProductTagMenuModel productTagMenuModel2) {

		if (productTagMenuModel1.getTagId() < productTagMenuModel2.getTagId()) {
			return 1;
		} else if (productTagMenuModel1.getTagId() > productTagMenuModel2.getTagId()) {
			return -1;
		}

		return 0;
	}
}
