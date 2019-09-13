package game.actions;

class ActionCheckFailedException extends RuntimeException
{
	private static final long serialVersionUID = 6429683150426135897L;
	private static final String ACTION_CHECK_FAILED = "Action check failed.";

	public ActionCheckFailedException(Throwable cause) { super(ACTION_CHECK_FAILED, cause); }

	public ActionCheckFailedException() { super(ACTION_CHECK_FAILED); }
	
	
}
