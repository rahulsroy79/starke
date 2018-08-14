import { Moment } from 'moment';

export interface IEntityReviewsMySuffix {
    id?: number;
    rating?: number;
    reviewid?: number;
    entityid?: number;
    reviewdate?: Moment;
    userid?: number;
    subject?: string;
    detail?: string;
    ismarked?: number;
    isdeleted?: number;
    starkeEntityId?: number;
}

export class EntityReviewsMySuffix implements IEntityReviewsMySuffix {
    constructor(
        public id?: number,
        public rating?: number,
        public reviewid?: number,
        public entityid?: number,
        public reviewdate?: Moment,
        public userid?: number,
        public subject?: string,
        public detail?: string,
        public ismarked?: number,
        public isdeleted?: number,
        public starkeEntityId?: number
    ) {}
}
