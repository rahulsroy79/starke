import { BaseEntity } from '../../models';

export class StarkeEntity implements BaseEntity {
    constructor(
        public id?: number,
        public entityid?: number,
        public entityname?: string,
        public entitytypeid?: number,
        public istopentity?: number,
        public parententityid?: number,
        public allowrating?: number,
        public allowreview?: number,
        public entitycreationdate?: any,
        public entitybriefdescription?: string,
        public entityimageContentType?: string,
        public entityimage?: any,
        public entityPageDetailsId?: number,
        public entityDetailsId?: number,
        public entityReviews?: BaseEntity[],
        public parentId?: number,
    ) {
    }
}
