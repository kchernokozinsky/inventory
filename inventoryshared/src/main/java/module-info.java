module inventory.shared {
	requires jbcrypt;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.core;
	exports inventory.shared.Dto;
	exports inventory.shared.impl;
}