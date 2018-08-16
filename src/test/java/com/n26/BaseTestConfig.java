package com.n26;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Base test configuration like runner and other configuration.
 * @author Pratik
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(value = { "classpath:application-test.properties" })
public class BaseTestConfig {}
