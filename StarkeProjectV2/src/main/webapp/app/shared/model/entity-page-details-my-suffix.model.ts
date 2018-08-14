export interface IEntityPageDetailsMySuffix {
    id?: number;
    pagedetailid?: number;
    pagetypename?: string;
    entityPageFormatsPageformatid?: string;
    entityPageFormatsId?: number;
    starkeEntityId?: number;
}

export class EntityPageDetailsMySuffix implements IEntityPageDetailsMySuffix {
    constructor(
        public id?: number,
        public pagedetailid?: number,
        public pagetypename?: string,
        public entityPageFormatsPageformatid?: string,
        public entityPageFormatsId?: number,
        public starkeEntityId?: number
    ) {}
}
