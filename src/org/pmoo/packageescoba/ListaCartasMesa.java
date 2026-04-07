package org.pmoo.packageescoba;

public class ListaCartasMesa extends ListaCartas {

	public ListaCartasMesa() {
		super();
	}
	public boolean esEscoba()
	{
		boolean escoba = false;
		if (super.estaVacia()) {
			escoba = true;
		}
		return escoba;
	}
}
