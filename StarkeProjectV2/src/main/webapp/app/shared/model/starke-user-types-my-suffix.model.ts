export interface IStarkeUserTypesMySuffix {
    id?: number;
    usertypeid?: number;
    typename?: string;
    usertypedescription?: string;
    starkeUserId?: number;
}

export class StarkeUserTypesMySuffix implements IStarkeUserTypesMySuffix {
    constructor(
        public id?: number,
        public usertypeid?: number,
        public typename?: string,
        public usertypedescription?: string,
        public starkeUserId?: number
    ) {}
}
