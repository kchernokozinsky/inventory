package inventory.shared.Dto;

public enum ResponseErrorType {
	OK,
	FAILED_AUTH,
	ALREADY_EXIST,
	ENTITY_NOT_FOUND,
	JWT_CHECK_FAILED,
	EXPIRED_JWT
}
