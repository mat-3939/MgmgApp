package com.example.mgmgapp.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	private Integer id;
	private String name;
	private String description;
	private Integer price;
	private Integer stock;
	private String image_path;
	private Integer category_id;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
