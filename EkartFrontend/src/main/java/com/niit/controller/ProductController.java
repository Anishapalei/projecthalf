package com.niit.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.niit.dao.CategoryDAO;
import com.niit.dao.ProductDAO;
import com.niit.model.Category;
import com.niit.model.Product;

@Controller
public class ProductController 
{
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@RequestMapping(value="/product")
	public String showProductPage(Model m)
	{
		List<Product> listProducts=productDAO.listProducts();
		m.addAttribute("productlist", listProducts);
		
		Product product=new Product();
		m.addAttribute(product);
		
		m.addAttribute("categoryList", this.getCategoryList(categoryDAO.listCategories()));
		
		return "Product";
	}
	
	@RequestMapping(value="/InsertProduct",method=RequestMethod.POST)
	public String insertProduct(@ModelAttribute("product")Product product,@RequestParam("pimage")MultipartFile prodImage,Model m)
	{
		//Displaying Content
		System.out.println("Product Name:"+product.getProductName());
		System.out.println("Product Desc:"+product.getProductDesc());
		System.out.println("Category ID:"+product.getCategoryId());
		System.out.println("Price:"+product.getPrice());
		System.out.println("Stock:"+product.getStock());
		productDAO.addProduct(product);
		
		//Start-Image Insertion
		
		
		String path="D:\\Workspace\\Myworkspace\\EcommerceProject\\EkartFrontend\\src\\main\\webapp\\WEB-INF\\resources\\images\\";
		
		path=path+String.valueOf(product.getProductId())+".jpg";
		
		File imageFile=new File(path);
		
		if(!prodImage.isEmpty())
		{
			try
			{
				byte[] buffer=prodImage.getBytes();
				FileOutputStream fos=new FileOutputStream(imageFile);
				BufferedOutputStream bs=new BufferedOutputStream(fos);
				bs.write(buffer);
				bs.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
				m.addAttribute("Error","Exception Occured during the Image Uploading"+e);	
			}
		}
		else
		{
			System.out.println("error occured");
			m.addAttribute("Error","Error Occured during the Image Uploading");
		}
		//End-Image Insertion
		Product product1=new Product();
		m.addAttribute(product1);
		
		List<Product> listProducts=productDAO.listProducts();
		m.addAttribute("productlist", listProducts);
		
		m.addAttribute("categoryList", this.getCategoryList(categoryDAO.listCategories()));
		
		return "Product";
	}
	public LinkedHashMap<Integer,String> getCategoryList(List<Category> listCategory)	
	{
		LinkedHashMap<Integer,String> listCategories=new LinkedHashMap<Integer,String>();
		
		for(Category category:listCategory)
		{
			listCategories.put(category.getCategoryId(),category.getCategoryName());
		}
		return listCategories;
	}
		@RequestMapping(value="/productDisplay")
		public String productDisplay(Model m)
		{
			List<Product> listProducts=productDAO.listProducts();
			m.addAttribute("productlist", listProducts);
			
			return "ProductDisplay";
		}
		@RequestMapping(value="/totalProductDisplay/{product.productId}")
		public String productDisplay(@PathVariable("productId")int productId,Model m)
		{
			Product product=productDAO.getProduct(productId);
			m.addAttribute("product",product);
			
		
			return "TotalProductDisplay";
		}
	}






