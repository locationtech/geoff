package org.locationtech.geoff.ui;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.swt.widgets.Shell;

public class DialogsBuilder {

	private Object[] input;
	private String message;
	private boolean multi;
	private boolean modal;
	private AdapterFactory af;

	public static DialogsBuilder create() {
		return new DialogsBuilder();
	}

	public DialogsBuilder input(Object[] input) {
		this.input = input;
		return this;
	}

	public DialogsBuilder message(String message) {
		this.message = message;
		return this;
	}

	public DialogsBuilder multi(boolean multi) {
		this.multi = multi;
		return this;
	}

	public DialogsBuilder modal(boolean modal) {
		this.modal = modal;
		return this;
	}

	public DialogsBuilder adapterFactory(AdapterFactory af) {
		this.af = af;
		return this;
	}

	public Object[] getResult(Shell shell) {
		return null;
	}
}
