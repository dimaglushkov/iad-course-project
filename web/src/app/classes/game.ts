export class Game{
    id?: number;
    name: string;
    date: Date;
    description: string;
}

export class LibraryGame{
    gameId: number;
    gameName: string;
}

export class Library{
    personId?: number;
    games: LibraryGame[];
}