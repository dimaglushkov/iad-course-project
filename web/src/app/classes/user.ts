import { Info } from './info'
import { Library } from './game'
import { ResponseState } from './responseState'


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
    state: ResponseState;
    id: number;
    nickname: string;
    info: Info;
    library: Library;    

}