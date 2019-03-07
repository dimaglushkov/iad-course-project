import { StringifyOptions } from 'querystring';

export interface ResponseState{
    success: boolean;
    description: string;
}

export interface Review{
    id: number;
    gameId: number;
    gameName: string;
    gameRate: number;
    desc: string;
}

export interface ReviewResponse{
    success: boolean;
    description: string;
    reviews?: Review[];
}

export interface Message{
    id: number;
    from: string;
    to: string;
    topic: string;
    text: string;
    date: string;
}

export interface MessageList{
    state: ResponseState;
    messages?: Message[];
}

export interface PersonResponse{
    success: boolean;
    description: string;
    id: number;
    email: string;
    registerDate: string;
    numOfFriends: number;
    numOfGames: number;
    numOfReviews: number;
    name?: string;
    surname?: string;
    country?: string;
    city?: string;
    birthDate: string;
}

export interface Friend{
    friendname: string;
}

export interface FriendResponse{
    success: boolean;
    description: string;
    friends: Friend[];
}

export interface RightsCheck{
    success: boolean;
    description: string;
    admin: boolean;
}

export interface Game{
    gameId: number;
    gameName: string;
    gameDate?: string;
    gameDesc?: string;
}


export interface GameResponse{
    success: boolean;
    description: string;
    games?: Game[];
}


































export interface User{
    j_username: string
    j_password: string
}

export interface NewUser{
    nickname: string 
    email: string
    password: string
}


