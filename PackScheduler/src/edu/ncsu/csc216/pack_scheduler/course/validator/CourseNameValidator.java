package edu.ncsu.csc216.pack_scheduler.course.validator;

public class CourseNameValidator {

	/**
	 *  
	 */
	private final State INITIAL = new InitialState();
	private final State SUFFIX = new InitialState();
	private final State LETTER = new InitialState();
	private final State NUMBER = new InitialState();
	private State state = INITIAL;

	public boolean isValid(String s) throws InvalidTransitionException{
		state = INITIAL;
		int i=0;
		int numbercounter = 1;
		try {
			while(i < s.length()) {
				char ch = s.charAt(i++);
				if(state == INITIAL && Character.isLetter(ch) && i < LetterState.MAX_PREFIX_LETTERS) {
					state = LETTER;
					state.onLetter();
				} else if(Character.isDigit(ch)){
						if (numbercounter <= NumberState.COURSE_NUMBER_LENGTH) {
							state = NUMBER;
							state.onDigit();
						} else{
							state.onOther();
						}
				} else if (Character.isLetter(ch)) {
					state = SUFFIX;
					state.onLetter();
				}
			}
		}
		return false;
	}

	public abstract class State {
		public State() {

		}

		public abstract void onLetter() throws InvalidTransitionException;

		public abstract void onDigit() throws InvalidTransitionException;

		/**
		 * 
		 * @throws InvalidTransitionException
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}

	public class InitialState extends State {
		private InitialState() {

		}

		@Override
		public void onLetter() {
			state = LETTER;
		}

		/**
		 * @throws InvalidTransitionException
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException();
		}
	}

	public class LetterState extends State {
		private static final int MAX_PREFIX_LETTERS = 4;

		private LetterState() {

		}

		@Override
		public void onLetter() {
			state = LETTER;
		}

		/**
		 * @throws InvalidTransitionException
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			state = NUMBER;
		}
	}

	public class NumberState extends State {
		private static final int COURSE_NUMBER_LENGTH = 3;

		private NumberState() {

		}

		/**
		 * @throws InvalidTransitionException
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException();
		}

		/**
		 * @throws InvalidTransitionException
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			state = NUMBER;
		}
	}

	public class SuffixState extends State {
		private SuffixState() {

		}

		/**
		 * @throws InvalidTransitionException
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException();
		}

		/**
		 * @throws InvalidTransitionException
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException();
		}
	}
}
