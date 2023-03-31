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
  vulnerabilité: Vulnerabilite;
  recolteur: Recolteur;
}

export enum Vulnerabilite {}

export class Production {}
