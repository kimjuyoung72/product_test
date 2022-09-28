package com.kh.demo.test;

import com.kh.demo.dao.Product;
import com.kh.demo.dao.ProductDAO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class JunitTest {
  @Autowired
  private ProductDAO productDAO;
  private static Product compareProduct;
//  //상품등록
//  Long save(Product product);
//  //상품목록
//  List<Product> findAll();
//  //상품조회
//  Optional<Product> findByProductId(Long ProductId);
//  //상품변경
//  int update(Long ProductId,Product product);
//  //상품삭제
//  int deleteByProductId(Long productId);
  //동록
  @Test
  @DisplayName("등록")
  void save() {
    Product product = new Product();
    product.setPname("키보드");
    product.setQuantity(5l);
    product.setPrice(12000l);
    Long pid = productDAO.save(product);
    log.info("pid={}", pid);
    Assertions.assertThat(pid).isEqualTo(372l);
  }
  //조회
  @Test
  @DisplayName("조회")
  void findById() {
    Optional<Product> findedProduct = productDAO.findByProductId(371l);
    Assertions.assertThat(findedProduct.get().getPname()).isEqualTo("키보드");
    Assertions.assertThat(findedProduct.get().getQuantity()).isEqualTo(5l);
  }
  //수정
  @Test
  @DisplayName("수정")
  void update() {
    Long pid = 372l;
    Product product = new Product();
    product.setProductId(pid);
    product.setPname("키보드");
    product.setQuantity(8l);
    product.setPrice(15000l);
    int updatedRow = productDAO.update(pid, product);
    Assertions.assertThat(updatedRow).isEqualTo(1);
  }
  //삭제
  @Test
  @DisplayName("삭제")
  void del() {
    Long pid = 372l;
    int deletedRow = productDAO.deleteByProductId(pid);
    Assertions.assertThat(deletedRow).isEqualTo(1);

  }
  //목록
  @Test
  @DisplayName("목록")
  void all() {
    List<Product> list = productDAO.findAll();
    Assertions.assertThat(list.size()).isGreaterThan(300);
  }
}
