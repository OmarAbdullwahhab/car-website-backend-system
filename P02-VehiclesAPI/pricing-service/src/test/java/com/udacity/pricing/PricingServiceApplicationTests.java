package com.udacity.pricing;

import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.repository.PriceRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricingServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private PriceRepository priceRepository;

	@Test
	public void contextLoads() {
	}
	@BeforeEach
	public void generateOneRecord()
	{
		if(!this.priceRepository.findById(1l).isPresent())
		{
			Price p = new Price( "SAR", new BigDecimal("25000.00"), 1l);
			this.priceRepository.save(p);
		}

	}
	@Test
	public void addPriceTest()
	{
		var count = this.priceRepository.count();

		Price p = new Price( "SAR", new BigDecimal("25000.00"), 1l);
		this.priceRepository.save(p);
		assertEquals(count + 1, this.priceRepository.count());

	}
	@Test
	public void updatePriceTest()
	{
		var record = this.priceRepository.findById(1l);
		assertTrue(record.isPresent());
		var item = record.get();
		var price = item.getPrice();
		item.setPrice(price.add(new BigDecimal("2")));
		this.priceRepository.save(item);
		record = this.priceRepository.findById(1l);
		assertTrue(!record.get().getPrice().equals(price));
		assertFalse(record.get().getPrice().equals(price));

	}

	@Test
	public void deletePriceTest()
	{
		var record = this.priceRepository.findById(1l);
		assertTrue(record.isPresent());
		var item = record.get();
		this.priceRepository.delete(item);
		record = this.priceRepository.findById(1l);
		assertTrue(!record.isPresent());
		assertFalse(record.isPresent());
	}

	@Test
	public void findPriceTest()
	{
		var record = this.priceRepository.findById(1l);
		assertTrue(record.isPresent());
	}
}
