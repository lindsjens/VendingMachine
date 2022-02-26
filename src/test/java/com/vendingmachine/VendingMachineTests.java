package com.vendingmachine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.vendingmachine.view.Menu;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VendingMachineTests {

	private VendingMachine vendingMachine;

	@Before
	public void setup() {
		vendingMachine = new VendingMachine();
	}

	@Test
	public void displays_a_list_of_menu_options_and_prompts_user_to_make_a_choice() {




	}


	@Test
	public void returns_object_corresponding_to_user_choice() {

	}

	@Test
	public void redisplays_menu_if_user_does_not_choose_valid_option() {

	}

	@Test
	public void redisplays_menu_if_user_chooses_option_less_than_1() {

	}

	@Test
	public void redisplays_menu_if_user_enters_garbage() {

	}

	private Menu getMenuForTestingWithUserInput(String userInput) {
		ByteArrayInputStream input = new ByteArrayInputStream(String.valueOf(userInput).getBytes());
		//return new Menu(input, output);

		return null;
	}

	private Menu getMenuForTesting() {
		return getMenuForTestingWithUserInput("1" + System.lineSeparator());
	}
}
