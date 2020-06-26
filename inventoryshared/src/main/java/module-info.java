module inventory.shared {
	requires jbcrypt;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.core;
    requires jjwt;
	requires java.xml.bind;
	exports inventory.shared.Dto;
	exports inventory.shared.impl;
	exports inventory.shared.api;
}