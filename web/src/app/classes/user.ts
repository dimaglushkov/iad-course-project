import { Info } from './info'

export class LoginUser{
    j_username: string
    j_password: string  
}

export class RegistrationUser{
    nickname: string 
    email: string
    password: string
}

export class User{
    id: number;
    nickname: string;
    info: Info;
    

}