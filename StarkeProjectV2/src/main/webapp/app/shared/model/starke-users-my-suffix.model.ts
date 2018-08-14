import { Moment } from 'moment';

export interface IStarkeUsersMySuffix {
    id?: number;
    userid?: number;
    username?: string;
    useremail?: string;
    isactive?: number;
    usertypeid?: number;
    activesince?: Moment;
    registrationid?: number;
    starkeUserTypesId?: number;
}

export class StarkeUsersMySuffix implements IStarkeUsersMySuffix {
    constructor(
        public id?: number,
        public userid?: number,
        public username?: string,
        public useremail?: string,
        public isactive?: number,
        public usertypeid?: number,
        public activesince?: Moment,
        public registrationid?: number,
        public starkeUserTypesId?: number
    ) {}
}
