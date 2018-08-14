import { Moment } from 'moment';
import { IEntityReviewsMySuffix } from 'app/shared/model//entity-reviews-my-suffix.model';

export interface IStarkeEntityMySuffix {
    id?: number;
    entityid?: number;
    entityname?: string;
    entitytypeid?: number;
    istopentity?: number;
    parententityid?: number;
    allowrating?: number;
    allowreview?: number;
    entitycreationdate?: Moment;
    entitybriefdescription?: string;
    entityimageContentType?: string;
    entityimage?: any;
    entityPageDetailsPagedetailid?: string;
    entityPageDetailsId?: number;
    entityDetailsEntityid?: string;
    entityDetailsId?: number;
    entityReviews?: IEntityReviewsMySuffix[];
    parentId?: number;
}

export class StarkeEntityMySuffix implements IStarkeEntityMySuffix {
    constructor(
        public id?: number,
        public entityid?: number,
        public entityname?: string,
        public entitytypeid?: number,
        public istopentity?: number,
        public parententityid?: number,
        public allowrating?: number,
        public allowreview?: number,
        public entitycreationdate?: Moment,
        public entitybriefdescription?: string,
        public entityimageContentType?: string,
        public entityimage?: any,
        public entityPageDetailsPagedetailid?: string,
        public entityPageDetailsId?: number,
        public entityDetailsEntityid?: string,
        public entityDetailsId?: number,
        public entityReviews?: IEntityReviewsMySuffix[],
        public parentId?: number
    ) {}
}
