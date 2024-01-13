import Food from "./Food";

// + role , userName, password
export default interface Person {
    firstName : string,
    lastName : string,
    contact : string,
    birthDate : Date,
    food : Food,
    image: string,
}