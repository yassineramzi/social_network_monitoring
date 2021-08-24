export class JwtResponse {
  public token: string;
  public type: string;
  public id: number;
  public username: string;
  public email: string;
  public roles: Array<string>;
}
