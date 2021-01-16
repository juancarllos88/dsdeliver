package com.devsuperior.dsdelivery.service;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import com.devsuperior.dsdelivery.dto.OrderDTO;
import com.devsuperior.dsdelivery.dto.ProductDTO;
import com.devsuperior.dsdelivery.model.Order;
import com.devsuperior.dsdelivery.model.OrderStatus;
import com.devsuperior.dsdelivery.model.Product;

@Service
public class ConverterServiceImpl {

	@Autowired
    private EntityManager em;

    public <T> SimpleJpaRepository<T, Long> create(Class<T> domainClass) {
        return new SimpleJpaRepository<>(domainClass, em);
    }
    
	
	public List<ProductDTO> converterToProductDTO(List<Product> products) {
		return products.stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
	}

	public List<OrderDTO> converterToOrderDTO(List<Order> orders) {
		return orders.stream().map(order -> new OrderDTO(order)).collect(Collectors.toList());
	}
	
	public OrderDTO converterToOrderDTO(Order order) {
		return new OrderDTO(order);
	}
	
	public Order converterToOrder(OrderDTO orderDTO) {
		Order order = new Order(orderDTO.getAddress(), orderDTO.getLatitude(), orderDTO.getLongitude(),
				Instant.now(), OrderStatus.PENDING);
		findEntityDependent(orderDTO, order, "products", Product.class);
		return order;
		
	}
	
	

	public static void main(String[] args) throws Exception {
		OrderDTO dto = new OrderDTO();
		ProductDTO p = new ProductDTO();
		p.setId(9L);
		ProductDTO p2 = new ProductDTO();
		p2.setId(19L);
		dto.setProducts(Arrays.asList(p,p2));
		
		Field[] declaredFields1 = dto.getClass().getDeclaredFields();
		for (Field field : declaredFields1) {
			field.setAccessible(true);
			System.out.println(field.getName());
//			if(field.getName().equals("id")) {
//				ids.add((Long)field.get(fieldDTO));
//			}
		}
		
		System.out.println("----------------------");
		
		Field fieldDTO = dto.getClass().getDeclaredField("products");
		fieldDTO.setAccessible(true);
		System.out.println(fieldDTO.getName());
		System.out.println("----------------------");
		
		if (fieldDTO.get(dto) instanceof Collection) {
			
//			Iterator it = ((Collection) fieldDTO.get(dto)).iterator();
//		      while (it.hasNext()) {
//		    	  Object objeto_para_passar = (it.next());
//		          Class<? extends Object> cls = objeto_para_passar.getClass();
//		          Field[] camposDaClasse = cls.getDeclaredFields();
//		          for (Field field : camposDaClasse) {
//						field.setAccessible(true);
//						System.out.println(field.getName());
//					}
//
//		    	  
//		      }
			
			List<Long> ids = new ArrayList<>();
			Iterator<?> it = ((Collection<?>) fieldDTO.get(dto)).iterator();
		      while (it.hasNext()) {
		    	  Object objeto_para_passar = (it.next());
		          Class<? extends Object> cls = objeto_para_passar.getClass();
		          Field[] camposDaClasse = cls.getDeclaredFields();
		          for (Field field : camposDaClasse) {
						field.setAccessible(true);
						if (field.getName().equals("id")) {
							System.out.println(field.getName());
							System.out.println(field.get(objeto_para_passar));
							break;
						}
					}

		    	  
		      }
			
		}
	}
	
	public <T> T findEntityDependent(Object dto, T entity, String entityField, Class<?> classDependent) {
		try {
			SimpleJpaRepository<?, Long> repository = create(classDependent);
			Field fieldEntity = entity.getClass().getDeclaredField(entityField);
			fieldEntity.setAccessible(true);
			
			Field fieldDTO = dto.getClass().getDeclaredField(entityField);
			fieldDTO.setAccessible(true);
			
			if (fieldDTO.get(dto) instanceof Collection) {
				
				List<Long> ids = new ArrayList<>();
				Iterator<?> it = ((Collection<?>) fieldDTO.get(dto)).iterator();
				while (it.hasNext()) {
					Object obj = (it.next());
					Class<? extends Object> cls = obj.getClass();
					Field[] fieldsObj = cls.getDeclaredFields();
					for (Field field : fieldsObj) {
						field.setAccessible(true);
						if (field.getName().equals("id")) {
							ids.add((Long) field.get(obj));
							break;
						}
					}

				}
				
				fieldEntity.set(entity, new HashSet<>(repository.findAllById(ids)));
				
				
			
			}else if( fieldDTO.get(dto)!=null) {
				
				fieldEntity.set(entity, repository.getOne((Long) fieldDTO.get(dto)));
			}
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return entity;
		
	}

}
