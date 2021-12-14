package fr.istic.prg1.tree;

import java.util.Scanner;

import fr.istic.prg1.tree_util.AbstractImage;
import fr.istic.prg1.tree_util.Iterator;
import fr.istic.prg1.tree_util.Node;
import fr.istic.prg1.tree_util.NodeType;

/**
 * @authors Emma Demure <emma.demure@etudiant.univ-rennes1.fr> Bahdon Barkhad <bahdon.barkhad@etudiant.univ-rennes1.fr> Gabriel Renault <gabriel.renault.1@etudiant.univ-rennes1.fr>
 * @version 5.0
 * @since 2016-04-20
 * 
 *        Classe dÃ©crivant les images en noir et blanc de 256 sur 256 pixels
 *        sous forme d'arbres binaires.
 * 
 */

public class Image extends AbstractImage {
	private static final Scanner standardInput = new Scanner(System.in);

	public Image() {
		super();
	}

	public static void closeAll() {
		standardInput.close();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param x_coinhg
	 * @param y_coinhg
	 * @param x_coinbd
	 * @param y_coinbd
	 * @return true si le point (x,y) appartient à la figure délimitée par (x_coinhg,y_coinhg) et (x_coinbd,y_coinbd)
	 */
	/*public boolean appartient(int x,int y,int x_coinhg,int
			y_coinhg,int x_coinbd,int y_coinbd) {
		return (x_coinhg <= x && x <= x_coinbd && y_coinhg <= y
				&& y <= y_coinbd);
	}

	/**
	 * @param x
	 *            abscisse du point
	 * @param y
	 *            ordonnÃ©e du point
	 * @pre !this.isEmpty()
	 * @return true, si le point (x, y) est allumÃ© dans this, false sinon
	 */
	@Override
	public boolean isPixelOn(int x, int y) {
		Iterator<Node> it = this.iterator();
		boolean horizontal=true;
		// on est sur la racine
		int x_min = 0; int y_min = 0;
		int x_max = 255; int y_max= 255;
		if(x_min<=x && y_min<=y && x<=x_max && y<=y_max) {
			// on vérifie si le point (x,y) est dans la fenêtre d’abord
			while(it.nodeType() != NodeType.SENTINEL) {
				switch(it.getValue().state) {
				case 0:
					return false;
				case 1:
					return true;
				case 2:
					if(horizontal) {
						// on découpe horizontalement

						if(y<=(y_max+y_min)/2) {
							// on va sur le demi rectangle haut
							y_max=y_max-(y_max-y_min)/2 - 1;
							it.goLeft();
						}
						else {
							// on va sur le demi rectangle bas
							y_min=(y_max-y_min)/2 + y_min + 1;
							it.goRight();
						}
						horizontal=false;
					}
					else { // on découpe verticalement
						if(x<=(x_max+x_min)/2) {
							// on va sur le carré gauche
							x_max=x_max-(x_max-x_min)/2 - 1;
							it.goLeft();
						}
						else { // on va sur le carré droit
							x_min=(x_max-x_min)/2+x_min+1;
							it.goRight();
						}
						horizontal=true;
					}
					break;
				}
			}
		}
		return false;
	}

	/**
	 * this devient identique Ã  image2.
	 *
	 * @param image2
	 *            image Ã  copier
	 *
	 * @pre !image2.isEmpty()
	 */

	public void affect ( AbstractImage image ) {
		Iterator<Node> itCopie = this.iterator();
		Iterator<Node> itSource = image.iterator();
		itCopie.clear();
		affectAux ( itSource , itCopie );
	}
	private static void affectAux (Iterator<Node> itSource,
			Iterator<Node> itCopie){
		switch ( itSource.nodeType() ) {
		case SENTINEL :
			break;
		default:
			itCopie.addValue( itSource.getValue() );

			itCopie.goLeft();
			itSource.goLeft();
			affectAux ( itSource , itCopie );
			itSource.goUp();
			itCopie.goUp();

			itCopie.goRight();
			itSource.goRight();
			affectAux ( itSource , itCopie );
			itSource.goUp();
			itCopie.goUp();
			break;
		}
	}

	/**
	 * this devient rotation de image2 Ã  180 degrÃ©s.
	 *
	 * @param image2
	 *            image pour rotation
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void rotate180(AbstractImage image2) {
		assert(!image2.isEmpty());

		Iterator<Node> it = this.iterator();
		Iterator<Node> itbis = image2.iterator();
		it.clear();
		rotate180Aux(it,itbis);
	}

	private void rotate180Aux(Iterator<Node> it, Iterator<Node> itbis) {
		// on doit inverser TOUTES les branches : pour cela, on fait un parcours infixe sur it et un parcours infixe inversé sur itbis
		if(itbis.nodeType() != NodeType.SENTINEL) {
			it.addValue(Node.valueOf(itbis.getValue().state));
			it.goLeft(); itbis.goRight();

			rotate180Aux(it,itbis);

			it.goUp(); itbis.goUp();
			it.goRight(); itbis.goLeft();

			rotate180Aux(it,itbis);

			it.goUp(); itbis.goUp();
		}
	}

	/**
	 * this devient rotation de image2 Ã  90 degrÃ©s dans le sens des aiguilles
	 * d'une montre.
	 *
	 * @param image2
	 *            image pour rotation
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void rotate90(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction non demeandée");
		System.out.println("-------------------------------------------------");
		System.out.println();	    
	}

	/**
	 * this devient inverse vidÃ©o de this, pixel par pixel.
	 *
	 * @pre !image.isEmpty()
	 */
	@Override
	public void videoInverse() {

		assert(!this.isEmpty());

		Iterator<Node> it1 = this.iterator();
		videoInverseAux(it1);
	}

	public void videoInverseAux(Iterator<Node> it1) {

		assert(!it1.isEmpty());

		if(it1.getValue() == Node.valueOf(1)) {
			it1.setValue(Node.valueOf(0));
		}

		else if(it1.getValue() == Node.valueOf(0)) {
			it1.setValue(Node.valueOf(1));
		}

		else { //state = 2
			it1.goLeft();
			videoInverseAux(it1);
			it1.goUp(); 
			it1.goRight();
			videoInverseAux(it1);
			it1.goUp();
		}
	}

	/**
	 * this devient image miroir verticale de image2.
	 *
	 * @param image2
	 *            image Ã  agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorV(AbstractImage image2) {
		assert(!image2.isEmpty());
		Iterator<Node> it0 = this.iterator();
		Iterator<Node> it1 = image2.iterator();
		it0.clear();
		mirrorAux(it0, it1,true);
	}

	/**
	 * this devient image miroir horizontale de image2.
	 *
	 * @param image2
	 *            image Ã  agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorH(AbstractImage image2) {
		assert(!image2.isEmpty());
		Iterator<Node> it0 = this.iterator();
		Iterator<Node> it1 = image2.iterator();
		it0.clear();
		mirrorAux(it0, it1,false);
	}

	private void mirrorAux(Iterator<Node> it0, Iterator<Node> it1,boolean vertical) {
		if(it1.nodeType() != NodeType.SENTINEL) {
			if(vertical) {
				it0.addValue(Node.valueOf(it1.getValue().state));
				it0.goLeft(); it1.goRight();

				mirrorAux(it0,it1,false);
				it0.goUp(); it1.goUp();
				it0.goRight(); it1.goLeft();

				mirrorAux(it0,it1,false);

				it0.goUp(); it1.goUp();
			}
			else {
				it0.addValue(Node.valueOf(it1.getValue().state));
				it0.goLeft(); it1.goLeft();

				mirrorAux(it0,it1,true);
				it0.goUp(); it1.goUp();
				it0.goRight(); it1.goRight();

				mirrorAux(it0,it1,true);

				it0.goUp(); it1.goUp();
			}
		}
	}


	/**
	 * this devient quart supÃ©rieur gauche de image2.
	 *
	 * @param image2
	 *            image Ã  agrandir
	 * 
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomIn(AbstractImage image2) {

		assert(!image2.isEmpty());
		Iterator<Node> it0 = this.iterator();
		Iterator <Node> it1 = image2.iterator();
		it0.clear();
		zoomInAux(it0,it1);
	}

	private void zoomInAux(Iterator<Node> it0, Iterator<Node> it1) {

		if(it1.getValue().state == 1 || it1.getValue().state == 0) { //Si toute l'image est soit éteinte soit allumée (root=1 ou root=0)
			it0.addValue(it1.getValue());									//Le quart supérieur gauche l'est aussi
			return;
		}

		it1.goLeft(); //On entre dans le ss-arbre gauche de image2

		if(it1.getValue().state == 1 || it1.getValue().state == 0) { // Si la moitié haute de l'image est soit éteinte soit allumée
			it0.addValue(it1.getValue());									//Le quart supérieur gauche l'est aussi
			return;
		}

		it1.goLeft();
		affectAux(it1, it0);

	}

	/**
	 * Le quart supérieur gauche de this devient image2, le reste de this
	 * devient éteint.
	 * 
	 * @param image2
	 *            image à réduire
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomOut(AbstractImage image2) {

		assert(!image2.isEmpty());
		Iterator<Node> it0 = this.iterator();
		Iterator <Node> it1 = image2.iterator();
		zoomOutAux(it0,it1);
	}

	private void zoomOutAux(Iterator<Node> it0, Iterator<Node> it1) {

		if(!it0.isEmpty()) {
			if(it0.getValue().state == 1 || it0.getValue().state == 0) { //Si toute l'image est soit éteinte soit allumée (root=1 ou root=0)
				it1.addValue(it0.getValue());									//Le quart supérieur gauche l'est aussi
				return;
			}

			it0.goLeft(); //On entre dans le ss-arbre gauche de this

			if(it0.getValue().state == 1 || it0.getValue().state == 0) { // Si la moitié haute de l'image est soit éteinte soit allumée
				it1.addValue(it0.getValue());									//Le quart supérieur gauche l'est aussi
				return;
			}

			it0.goLeft();
			affectAux(it0,it1);

			//il faut maintenant éteindre le reste de this
		}
	}

	/*
	 * AUTRE PISTE POUR ZOOMOUT
	 * 
	 * /**
	 * Le quart supérieur gauche de this devient image2, le reste de this
	 * devient éteint.
	 * 
	 * @param image2
	 *            image à réduire
	 * @pre !image2.isEmpty()
	 /
	@Override
	public void zoomOut(AbstractImage image2) {

		assert(!image2.isEmpty());

		Iterator<Node> it0 = this.iterator();
		Iterator <Node> it1 = image2.iterator();

		it0.clear(); //On s'assure que this est vide

		it0.addValue(Node.valueOf(2)); //On replace la racine

		it0.goRight();
		it0.addValue(Node.valueOf(0)); //La moitié basse du carré est éteinte
		it0.goUp();
		it0.goLeft();
		it0.addValue(Node.valueOf(2));  
		it0.goRight();
		it0.addValue(Node.valueOf(0)); // Le carré supérieur droit est éteint
		it0.goUp();
		it0.goLeft();               	 //On déplace it0 sur le coin supérieur gauche de l'image
		affectAux(it0, it1);        	// On copie image2 dans ce coin supérieur gauche
		it0.goRoot();
		it0.goLeft(); it0.goLeft();
		//zoomOutAux(it0,0);				//On appelle zoomOutAux pour réduire l'image (this) si elle est trop grande
	}

	public void zoomOutAux(Iterator<Node> it0, int hauteur) {

		/*if (!it0.isEmpty() || hauteur <= 16) { //si la profondeur de l'arbre excède 16 l'image à coller dans le coin supérieur gauche est trop grande
		it0.goLeft(); 				//parcours infixe classique
		zoomOutAux(it0, hauteur); 
		it0.goUp(); 
		hauteur++;
		it0.goRight();
		zoomOutAux(it0, hauteur);
		it0.goUp();
		}
	 */

	//L'it0 se place à l'étage de l'arbre où les pixels sont à simplifier et pour ce, on fait la moyenne des feuilles de
	// chaque noeud double.
	//(1+0)/2 = 0,5 => Le pixel est allumé



	/**
	 * this devient l'intersection de image1 et image2 au sens des pixels
	 * allumÃ©s.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void intersection(AbstractImage image1, AbstractImage image2) {
		assert(this != image1 && this != image2);
		Iterator<Node> it0 = this.iterator();
		Iterator <Node> it1 = image1.iterator();
		Iterator<Node> it2 = image2.iterator();
		it0.clear();
		intersecAux(it0,it1,it2);
	}

	/**
	 * it0 devient l'intersection de it1 et it2
	 * 
	 * @pre !it1.isEmpty() && !it2.isEmpty()
	 * 
	 * @param it0 itérateur de this
	 * @param it1 itérateur de la première image
	 * @param it2 itérateur de la seconde image
	 * 
	 * @note 0 inter n'importe quoi = 0 | 2 inter (2 ou 1) = 2 | 1 inter 1 (uniquement) = 1
	 */
	private void intersecAux(Iterator<Node> it0, Iterator<Node> it1, Iterator<Node> it2) {
		// même principe que l'union sûrement ?

		assert(!it1.isEmpty() && !it2.isEmpty());

		if(it1.getValue().state == 0 || it2.getValue().state == 0) {
			it0.addValue(Node.valueOf(0));
		}

		else if(it1.getValue().state == 1 && it2.getValue().state == 1) {
			it0.addValue(Node.valueOf(1));
		}

		else if(it1.getValue().state == 2 && it2.getValue().state == 1) {
			affectAux(it1,it0);
		}
		else if(it1.getValue().state == 1 && it2.getValue().state == 2) {
			affectAux(it2,it0);
		}
		else  { //cas où state1 = state2 = 2
			it0.addValue(Node.valueOf(2));
			it0.goLeft(); it1.goLeft(); it2.goLeft();   //Parcours des sous-arbres gauches this, image1 et image2

			intersecAux(it0,it1, it2);           //Appel récursif sur les fils gauches de it1 et it2

			it0.goUp(); it1.goUp(); it2.goUp();
			it0.goRight(); it1.goRight(); it2.goRight();   //Parcours des sous-arbres droits de image1 et image2

			intersecAux(it0,it1, it2);           //Appel récursif sur les fils droits de it1 et it2
			it0.goUp(); it1.goUp(); it2.goUp();

			if(it0.nodeType() == NodeType.DOUBLE) {
				it0.goRight();
				int ss_arbre_droit = it0.getValue().state;
				it0.goUp();
				it0.goLeft();
				int ss_arbre_gauche = it0.getValue().state;
				it0.goUp();
				// on a récupéré les valeurs des ss arbres droit et gauche
				if(ss_arbre_droit == ss_arbre_gauche && ss_arbre_droit != 2) {
					it0.setValue(Node.valueOf(ss_arbre_droit));
					it0.goLeft();
					it0.remove(); // on remonte au noeud grâce au remove normalement
					it0.goUp();
					it0.goRight();
					it0.remove(); // même principe
					it0.goUp();
				}
			}

		}
	}

	/**
	 * this devient l'union de image1 et image2 au sens des pixels allumÃ©s.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void union(AbstractImage image1, AbstractImage image2) {
		assert(this != image1 && this != image2);
		Iterator<Node> it0 = this.iterator();
		Iterator<Node> it1 = image1.iterator();
		Iterator<Node> it2 = image2.iterator();
		it0.clear();
		unionAux(it0,it1, it2);
	}

	/**
	 * it0 devient l'union de it1 et it2
	 * 
	 * @pre !it1.isEmpty() && !it2.isEmpty()
	 * 
	 * @param it0 itérateur de this
	 * @param it1 itérateur de la première image
	 * @param it2 itérateur de la seconde image
	 * 
	 * @note 1 union n'importe quoi = 1 | 2 union (2 ou 0) = 2 | 0 union 0 (uniquement) = 0
	 */
	private void unionAux(Iterator<Node> it0, Iterator<Node> it1, Iterator<Node> it2){
		assert(!it1.isEmpty() && !it2.isEmpty());

		if(it1.getValue().state == 1 || it2.getValue().state == 1){
			it0.addValue(Node.valueOf(1));
			return; // pas la peine de parcourir les sous-arbres
		}

		else if(it1.getValue().state == 0 && it2.getValue().state == 0){
			it0.addValue(Node.valueOf(0));
			return; // pas la peine de parcourir les sous-arbres
		}

		else if(it1.getValue().state == 2 && it2.getValue().state == 0){
			affectAux(it1,it0);
		}

		else if(it1.getValue().state == 0 && it2.getValue().state == 2) {
			affectAux(it2,it0);
		}

		else { // cas où state1 == state2 == 2
			it0.addValue(Node.valueOf(2));
			it0.goLeft(); it1.goLeft(); it2.goLeft();   //Parcours des sous-arbres gauches this, image1 et image2

			unionAux(it0,it1, it2);           //Appel récursif sur les fils gauches de it1 et it2

			it0.goUp(); it1.goUp(); it2.goUp();
			it0.goRight(); it1.goRight(); it2.goRight();   //Parcours des sous-arbres droits de image1 et image2

			unionAux(it0,it1, it2);           //Appel récursif sur les fils droits de it1 et it2
			it0.goUp(); it1.goUp(); it2.goUp();

			//on doit maintenant regarder si it0 est simplifiable -> si les deux ss arbres de it0 ont la même valeur (O ou 1)
			if(it0.nodeType() == NodeType.DOUBLE) {
				it0.goRight();
				int ss_arbre_droit = it0.getValue().state;
				it0.goUp();
				it0.goLeft();
				int ss_arbre_gauche = it0.getValue().state;
				it0.goUp();
				// on a récupéré les valeurs des ss arbres droit et gauche
				if(ss_arbre_droit == ss_arbre_gauche && ss_arbre_droit != 2) {
					it0.setValue(Node.valueOf(ss_arbre_droit));
					it0.goLeft();
					it0.remove(); // on remonte au noeud grâce au remove normalement
					it0.goUp();
					it0.goRight();
					it0.remove(); // même principe
					it0.goUp();
				}
			}
		}
	}

	/**
	 * Attention : cette fonction ne doit pas utiliser la commande isPixelOn
	 * 
	 * @return true si tous les points de la forme (x, x) (avec 0 <= x <= 255)
	 *         sont allumÃ©s dans this, false sinon
	 */
	@Override
	public boolean testDiagonal() {
		Iterator<Node> it = this.iterator();
		if(!it.isEmpty()) {
			return testDiagAux(it);
		}
		return false;
	}

	private boolean testDiagAux(Iterator<Node> it) {
		/*
		 * on doit faire un parcours infixe sur les carrés/rectangles ayant pour coin un point (x,x)
		 * il faut pour cela commencer par le sous sous ... sous arbre gauche (figure tout en haut à gauche de l'image)
		 * et vérifier si le state de la dernière feuille est différent de 0
		 * si oui on continue notre parcours infixe en "remontant" deux fois puis en regardant le sous sous ... sous arbre droit du noeud parent
		 * qui correspond au carré qui suit (en bas à droite) sur la droite y=-x
		 * on réitère l'opération (il faut vérifier si on est sur un carré ou rectangle d'abord : profondeur pair ou impaire?)
		 */
		if(it.nodeType() != NodeType.SENTINEL) {
			if(it.getValue().state == 0) {
				return false;
			}
			else if(it.getValue().state == 1) {
				return true;
			}
			else { // state = 2

				it.goLeft();
				if(it.getValue().state == 0) {
					it.goUp();
					return false;
				}
				else {
					if(it.getValue().state == 2) {
						it.goLeft();
						if(!testDiagAux(it)) {
							return false;
						}
						it.goUp();
					}
					it.goUp(); // on go up pour le cas où state=1 également
				}

				it.goRight();
				if(it.getValue().state == 0) {
					it.goUp();
					return false;
				}
				else {
					if(it.getValue().state == 2) {
						it.goRight();
						if(!testDiagAux(it)) {
							return false;
						}
						it.goUp();
					}
					it.goUp(); // on go up pour le cas où state=1 également
				}

				//on a fini : 
				return true;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * @param x1
	 *            abscisse du premier point
	 * @param y1
	 *            ordonnÃ©e du premier point
	 * @param x2
	 *            abscisse du deuxiÃšme point
	 * @param y2
	 *            ordonnÃ©e du deuxiÃšme point
	 * @pre !this.isEmpty()
	 * @return true si les deux points (x1, y1) et (x2, y2) sont reprÃ©sentÃ©s par
	 *         la mÃªme feuille de this, false sinon
	 */
	@Override
	public boolean sameLeaf(int x1, int y1, int x2, int y2) {
		Iterator<Node> it = this.iterator();
		boolean horizontal=true;
		int x_min=0; int y_min=0; int x_max=255; int y_max=255;
		assert(!it.isEmpty());
		while(it.nodeType() == NodeType.DOUBLE) {
			if(horizontal) {
				if(y1 <= (y_min+y_max)/2 && y2 <= (y_min+y_max)/2) {
					it.goLeft(); // on se place dans le rectangle haut
					y_max=y_max-(y_max-y_min)/2 - 1;
				}
				else if(y1 > (y_min+y_max)/2 && y2 > (y_min+y_max)/2) {
					it.goRight(); // on se place dans le rectangle bas
					y_min=(y_max-y_min)/2 + y_min + 1;
				}
				else {
					return false;
				}
				horizontal=false;
			}
			else {
				if(x1 <= (x_max+x_min)/2 && x2 <= (x_min+x_max)/2) {
					it.goLeft(); // on se place dans carré gauche
					x_max=x_max-(x_max-x_min)/2 - 1;
				}
				else if(x1 > (x_max+x_min)/2 && x2 > (x_min+x_max)/2) {
					it.goRight(); // on se place dans le rectangle bas
					x_min=(x_max-x_min)/2+x_min+1;
				}
				else {
					return false;
				}
				horizontal=true;
			}

		}
		return true;
	}

	/**
	 * @param image2
	 *            autre image
	 * @pre !this.isEmpty() && !image2.isEmpty()
	 * @return true si this est incluse dans image2 au sens des pixels allumÃ©s
	 *         false sinon
	 */
	@Override
	public boolean isIncludedIn(AbstractImage image2) {
		assert(!this.isEmpty() && !image2.isEmpty());

		Iterator<Node> it1 = this.iterator();
		Iterator<Node> it2 = image2.iterator();

		return isIncludedAux(it1,it2);
	}

	private boolean isIncludedAux(Iterator<Node> it1, Iterator<Node> it2) {
		if(!it2.isEmpty()) {
			if(it1.getValue().state == 1 && it2.getValue().state != 1) {
				//pas d'inclusion car le on a un pixel allumé sur it1 alors que le même pixel (sur it2) contiendra des sous pixels allumés et éteints
				return false;
			}

			else if(it1.getValue().state == 1 && it2.getValue().state == 1) {
				return true;
			}
			//on a géré le cas state1 = 1 pour tous les state2

			else if(it1.getValue().state == 0) {
				//peu importe la valeur d'it2.state parce que l'inclusion se joue sur les pixels allumés et vide inclus dans n'importe quoi
				return true;
			}
			//on a géré le cas state1 = 0 pour tous les state2

			//il nous reste les cas 2-0, 2-1, 2-2
			else if(it1.getValue().state == 2 && it2.getValue().state == 0) {
				//on va forcément trouver une feuille à 1 dans it1 qui ne sera pas incluse dans it2 donc on return false
				return false;
			}
			else if(it1.getValue().state == 2 && it2.getValue().state == 1) {
				//on va forcément trouver une feuille à 1 dans it1 donc on return true
				return true;
			}
			else {
				//state1 = state2 = 2
				// parcours infixe sur it1 et it2
				it1.goLeft(); it2.goLeft();
				boolean inclusion1 = isIncludedAux(it1,it2);
				it1.goUp(); it2.goUp();
				it1.goRight(); it2.goRight();
				boolean inclusion2 = isIncludedAux(it1,it2);
				it1.goUp(); it2.goUp(); // fin du parcours infixe
				/*if(!inclusion1 || !inclusion2) {
					return false;
				}*/
				return inclusion1 && inclusion2;
			}
		}
		else {
			return false;
		}
	}

	/*
	 * 2E METHODE INCLUSION : A inclus dans B <=> A inter B = A
	 * 
	 *
	 /**
	 * @param image2
	 *            autre image
	 * @pre !this.isEmpty() && !image2.isEmpty()
	 * @return true si this est incluse dans image2 au sens des pixels allumés
	 *         false sinon
	 /
	@Override
	public boolean isIncludedIn(AbstractImage image2) {

		assert(!this.isEmpty() && !image2.isEmpty());

		AbstractImage copieThis1 = new Image();

		Iterator<Node> it1 = this.iterator();
		Iterator<Node> it2 = copieThis1.iterator();

		affectAux(it1, it2);

        intersection(copieThis1, image2);

		return this.comparison(image2);
	}

	/**
	 * 
	 * @param image2 
	 * 			image à comparer avec this
	 * @return true si images2 et this ont les mêmes arbres false sinon.
	 *
	 /
	public boolean comparison(AbstractImage image2) {
		Iterator<Node> it0 = this.iterator();
		Iterator<Node> it1 = image2.iterator();

		return !comparisonAux(it0,it1);
	}


	private boolean comparisonAux(Iterator<Node> it0, Iterator<Node> it1){

		//rend vrai si il y a des différences entre les neouds et faux si ce sont les mêmes.
		if(it0.isEmpty()) { return (!it1.isEmpty());}

		else {
			if(it1.isEmpty()) {return true;}
			else {
				it0.goLeft(); it1.goLeft();
				comparisonAux(it0, it1);
				it0.goUp(); it1.goUp();
				it0.goRight(); it1.goRight();
				comparisonAux(it0, it1);
				return ((it0.getValue() != it1.getValue()));
			}
		}
	}*/

}