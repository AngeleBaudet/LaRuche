export class User {
  type: string; 
  id: number;
  login: string;
  password: string;

  constructor(type?: string, id?: number, login?: string, password?: string) {
    this.type = type;
    this.id = id;
    this.login = login;
    this.password = password;
  }
}

export class Gestionnaire extends User {
  constructor(id?: number, login?: string, password?: string, type?: string, ) {
    super(type, id, login, password);
    this.type = 'gestionnaire';
  }
}

export class Recolteur extends User {
  constructor(id?: number, login?: string, password?: string,type?: string) {
    super(type, id, login, password);
    this.type = 'recolteur';
  }
}

export class UserRequest {
  id: number;
  login: string;
  password: string;
  type : string; 

  constructor(id: number, login: string, password: string, type: string) {
    this.id = id;
    this.login = login;
    this.password = password;
    this.type = type;
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

export class RucheRequest {
  id: number;
  cadre: number;
  limite: boolean;
  vulnerabilite: Vulnerabilite;
  recolteurId: number;

  constructor(id?:number, cadre?: number, limite?: boolean, vulnerabilite?: Vulnerabilite, recolteurId?: number){
    this.id = id;
    this.cadre = cadre;
    this.limite = limite;
    this.vulnerabilite = vulnerabilite;
    this.recolteurId = recolteurId;
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
