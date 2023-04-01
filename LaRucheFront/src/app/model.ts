export class User {
  id: number;
  login: string;
  password: string;

  constructor(id?: number, login?: string, password?: string) {
    this.id = id;
    this.login = login;
    this.password = password;
  }
}

export class Gestionnaire extends User {
  constructor(id?: number, login?: string, password?: string) {
    super(id, login, password);
  }
}

export class Recolteur extends User {
  constructor(id?: number, login?: string, password?: string) {
    super(id, login, password);
  }
}

export class Ruche {
  id: number;
  cadre: number;
  limite: boolean;
  vulnerabilite: Vulnerabilite;
  recolteur: Recolteur;
  production: Array<Production>;

  constructor(
    id?: number,
    cadre?: number,
    limite?: boolean,
    vulnerabilite?: Vulnerabilite,
    recolteur?: Recolteur,
    production?: Array<Production>
  ) {
    this.id = id;
    this.cadre = cadre;
    this.limite = limite;
    this.vulnerabilite = vulnerabilite;
    this.recolteur = recolteur;
    this.production = production;
  }
}

export class Production {
  id: number;
  stock: number;
  annee: Date;
  ruche: Ruche;
  prixKg: number;
  produit: Produit;
  recolteur: Recolteur;

  constructor(
    id?: number,
    stock?: number,
    annee?: Date,
    ruche?: Ruche,
    prixKg?: number,
    produit?: Produit,
    recolteur?: Recolteur
  ) {
    this.id = id;
    this.annee = annee;
    this.stock = stock;
    this.ruche = ruche;
    this.prixKg = prixKg;
    this.produit = produit;
    this.recolteur = recolteur;
  }
}

export enum Vulnerabilite {
  Parasites,
  Pesticides,
  Predateurs,
  Loques,
}

export enum Produit {
  Miel,
  Pollen,
  Cire,
  Gelee_Royale,
}
