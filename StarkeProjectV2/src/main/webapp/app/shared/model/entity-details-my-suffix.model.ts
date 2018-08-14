export interface IEntityDetailsMySuffix {
    id?: number;
    entityid?: number;
    entitydescription?: string;
    entitycontent?: string;
    entitycontent2?: string;
    entitycontent3?: string;
    starkeEntityId?: number;
}

export class EntityDetailsMySuffix implements IEntityDetailsMySuffix {
    constructor(
        public id?: number,
        public entityid?: number,
        public entitydescription?: string,
        public entitycontent?: string,
        public entitycontent2?: string,
        public entitycontent3?: string,
        public starkeEntityId?: number
    ) {}
}
